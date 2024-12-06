// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.epilogue.processor;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import java.util.Collection;

/**
 * Arrays of bytes, ints, flats, doubles, booleans, Strings, and struct-serializable objects can be
 * logged. No other array types - including multidimensional arrays - are loggable.
 */
public class ArrayHandler extends ElementHandler {
  private final StructHandler m_structHandler;
  private final LoggableHandler m_loggableHandler;
  private final TypeMirror m_javaLangString;

  protected ArrayHandler(ProcessingEnvironment processingEnv, Collection<? extends Element> loggedTypes) {
    super(processingEnv);

    // use a struct handler for managing struct arrays
    m_structHandler = new StructHandler(processingEnv);
    m_loggableHandler = new LoggableHandler(processingEnv, loggedTypes);
    m_javaLangString = lookupTypeElement(processingEnv, "java.lang.String").asType();
  }

  @Override
  public boolean isLoggable(Element element) {
    return dataType(element) instanceof ArrayType arr
        && isLoggableComponentType(arr.getComponentType(), false);
  }

  /**
   * Checks if an array containing elements of the given type can be logged.
   *
   * @param type the data type to check
   * @return true if an array like {@code type[]} can be logged, false otherwise
   */
  public boolean isLoggableComponentType(TypeMirror type, boolean excludeCustomLogged) {
    if (type instanceof PrimitiveType primitive) {
      return switch (primitive.getKind()) {
        case BYTE, INT, LONG, FLOAT, DOUBLE, BOOLEAN -> true;
        default -> false;
      };
    }

    return m_structHandler.isLoggableType(type)
        || m_processingEnv.getTypeUtils().isAssignable(type, m_javaLangString)
        || (!excludeCustomLogged && m_loggableHandler.isLoggableType(type));
  }

  @Override
  public String logInvocation(Element element) {
    var dataType = dataType(element);

    // known to be an array type (assuming isLoggable is checked first); this is a safe cast
    var componentType = ((ArrayType) dataType).getComponentType();

    if (m_structHandler.isLoggableType(componentType)) {
      // Struct arrays need to pass in the struct serializer
      return "backend.log(\""
          + loggedName(element)
          + "\", "
          + elementAccess(element)
          + ", "
          + m_structHandler.structAccess(componentType)
          + ")";
    } else if (m_loggableHandler.isLoggableType(componentType)) {
      var elementAccess = elementAccess(element);
      var logInvocation = m_loggableHandler.addLogPathSuffix(
          m_loggableHandler.logInvocation(element, componentType, elementAccess + "[i]"),
          "\"/\" + i"
      );
      return """
          for (int i = 0; i < %s.length; i++) {
            %s;
          }
        """.formatted(elementAccess, logInvocation);
    } else {
      // Primitive or string array
      return "backend.log(\"" + loggedName(element) + "\", " + elementAccess(element) + ")";
    }
  }
}
