#!/usr/bin/env python3

# This script generates the network communication interface for wpilibj.
#
# This script takes no arguments and should be invoked from either the gen
# directory or the root directory of the project.

from datetime import date
import os
import re
import subprocess


# Check that the current directory is part of a Git repository
def in_git_repo(directory):
    return subprocess.run(["git", "rev-parse"]).returncode == 0


def main():
    if not in_git_repo("."):
        print("Error: not invoked within a Git repository", file=sys.stderr)
        sys.exit(1)

    # Handle running in either the root or gen directories
    configPath = "."
    if os.getcwd().rpartition(os.sep)[2] == "gen":
        configPath = ".."

    outputName = configPath + \
        "/hal/src/generated/java/FRCNetComm.java"

    # Set initial copyright year and get current year
    year = "2016"
    currentYear = str(date.today().year)

    # Start writing output file
    with open(outputName + ".tmp", "w") as temp:
        # Write first line of comment
        temp.write("/*")
        for i in range(0, 76):
            temp.write("-")
        temp.write("*/\n")

        # Write second line of comment
        temp.write("/* Copyright (c) ")
        if year != currentYear:
            temp.write(year)
            temp.write("-")
        temp.write(currentYear)
        temp.write(" FIRST. All Rights Reserved.")
        for i in range(0, 24):
            temp.write(" ")
        if year == currentYear:
            for i in range(0, 5):
                temp.write(" ")
        temp.write("*/\n")

        # Write rest of lines of comment
        temp.write("""\
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*""")
        for i in range(0, 76):
            temp.write("-")
        temp.write("*/\n")

        # Write preamble
        temp.write("""
// Autogenerated by wpilibj_frcnetcomm.py. Do not manually edit this file.

package edu.wpi.first.wpilibj.hal;

/**
 * JNI wrapper for library <b>FRC_NetworkCommunication</b><br>.
 */
@SuppressWarnings({\"MethodName\", \"LineLength\"})
public class FRCNetComm extends JNIWrapper {
""")

        # Read enums from C++ source files
        firstEnum = True
        files = [
            "/ni-libraries/include/FRC_NetworkCommunication/LoadOut.h",
            "/hal/src/main/native/include/HAL/UsageReporting.h"
        ]
        for fileName in files:
            with open(configPath + fileName, "r") as cppSource:
                while True:
                    # Read until an enum is encountered
                    line = ""
                    pos = -1
                    while "enum" not in line:
                        line = cppSource.readline()
                        if line == "":
                            break

                    if line == "":
                        break

                    # If "{" is on next line, read next line
                    if "{" not in line:
                        line = cppSource.readline()

                    # Write enum to output file as interface
                    values = []
                    line = cppSource.readline()
                    while "}" not in line:
                        if line == "\n":
                            values.append("")
                        elif line[0] != "#":
                            try:
                                values.append(
                                    re.search("[^,]+", line.strip()).group())
                            except AttributeError:
                                # Ignore lines that don't match value regex
                                pass
                        line = cppSource.readline()

                    # Extract enum name
                    nameStart = 0
                    for i, c in enumerate(line):
                        if c != " " and c != "}":
                            nameStart = i
                            break
                    enumName = line[nameStart:len(line) - 2]

                    # Write comment for interface name
                    # Only add newline if not the first enum
                    if firstEnum == True:
                        firstEnum = False
                    else:
                        temp.write("\n")
                    temp.write("  /**\n   * ")

                    # Splits camelcase string into words
                    enumCamel = re.findall(r'[A-Z](?:[a-z]+|[A-Z]*(?=[A-Z]|$))',
                                           enumName)
                    temp.write(enumCamel[0] + " ")
                    for i in range(1, len(enumCamel)):
                        temp.write(enumCamel[i][0].lower() + \
                            enumCamel[i][1:len(enumCamel[i])] + " ")
                    temp.write(
                        "from " + os.path.basename(fileName) + "\n"
                        "   */\n"
                        "  @SuppressWarnings({\"TypeName\", \"PMD.ConstantsInInterface\"})\n"
                        "  public static final class " + enumName + " {\n"
                        "    private " + enumName + "() {\n    }\n\n")

                    # Write enum values
                    count = 0
                    for value in values:
                        # Pass through empty lines
                        if value == "":
                            temp.write("\n")
                            continue

                        if "=" not in value:
                            value = value + " = " + str(count)
                            count += 1

                        # Add scoping for values from a different enum
                        if enumName != "tModuleType" and "kModuleType" in value:
                            value = value.replace("kModuleType",
                                                  "tModuleType.kModuleType")
                        temp.write("    public static final int " +
                                   value[0:len(value)] + ";\n")

                    # Write end of enum
                    temp.write("  }\n")

        # Write closing brace for file
        temp.write("}\n")

    # Replace old output file
    try:
        os.remove(outputName)
    except OSError:
        pass
    os.rename(outputName + ".tmp", outputName)


if __name__ == "__main__":
    main()
