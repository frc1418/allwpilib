load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_file")

# Rules Python
http_archive(
    name = "rules_python",
    sha256 = "c68bdc4fbec25de5b5493b8819cfc877c4ea299c0dcb15c244c5a00208cde311",
    strip_prefix = "rules_python-0.31.0",
    url = "https://github.com/bazelbuild/rules_python/releases/download/0.31.0/rules_python-0.31.0.tar.gz",
)

load("@rules_python//python:repositories.bzl", "py_repositories", "python_register_toolchains")

py_repositories()

python_register_toolchains(
    name = "python_3_10",
    ignore_root_user_error = True,
    python_version = "3.10",
)

load("@rules_python//python:pip.bzl", "pip_parse")

pip_parse(
    name = "allwpilib_pip_deps",
    python_interpreter_target = "@python_3_10_host//:python",
    requirements_lock = "//:requirements_lock.txt",
)

load("@allwpilib_pip_deps//:requirements.bzl", "install_deps")

install_deps()

# Download Extra java rules
http_archive(
    name = "rules_jvm_external",
    sha256 = "08ea921df02ffe9924123b0686dc04fd0ff875710bfadb7ad42badb931b0fd50",
    strip_prefix = "rules_jvm_external-6.1",
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/6.1/rules_jvm_external-6.1.tar.gz",
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_artifacts = [
    "org.ejml:ejml-simple:0.43.1",
    "com.fasterxml.jackson.core:jackson-annotations:2.15.2",
    "com.fasterxml.jackson.core:jackson-core:2.15.2",
    "com.fasterxml.jackson.core:jackson-databind:2.15.2",
    "us.hebi.quickbuf:quickbuf-runtime:1.3.3",
    "com.google.code.gson:gson:2.10.1",
]

maven_install(
    name = "maven",
    artifacts = maven_artifacts,
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://frcmaven.wpi.edu/artifactory/release/",
    ],
)

# Setup aspect lib
http_archive(
    name = "aspect_bazel_lib",
    sha256 = "a8a92645e7298bbf538aa880131c6adb4cf6239bbd27230f077a00414d58e4ce",
    strip_prefix = "bazel-lib-2.7.2",
    url = "https://github.com/aspect-build/bazel-lib/releases/download/v2.7.2/bazel-lib-v2.7.2.tar.gz",
)

load("@aspect_bazel_lib//lib:repositories.bzl", "aspect_bazel_lib_dependencies")

aspect_bazel_lib_dependencies()

# Download toolchains
http_archive(
    name = "rules_bzlmodrio_toolchains",
    sha256 = "fe267e2af53c1def1e962700a9aeda9e8fdfa9fb46b72167c615ec0e25447dd6",
    url = "https://github.com/wpilibsuite/rules_bzlmodRio_toolchains/releases/download/2025-1/rules_bzlmodRio_toolchains-2025-1.tar.gz",
)

load("@rules_bzlmodrio_toolchains//:maven_deps.bzl", "setup_legacy_setup_toolchains_dependencies")

setup_legacy_setup_toolchains_dependencies()

load("@rules_bzlmodrio_toolchains//toolchains:load_toolchains.bzl", "load_toolchains")

load_toolchains()

#
http_archive(
    name = "rules_bzlmodrio_jdk",
    sha256 = "a00d5fa971fbcad8a17b1968cdc5350688397035e90b0cb94e040d375ecd97b4",
    url = "https://github.com/wpilibsuite/rules_bzlmodRio_jdk/releases/download/17.0.8.1-1/rules_bzlmodRio_jdk-17.0.8.1-1.tar.gz",
)

load("@rules_bzlmodrio_jdk//:maven_deps.bzl", "setup_legacy_setup_jdk_dependencies")

setup_legacy_setup_jdk_dependencies()

register_toolchains(
    "@local_roborio//:macos",
    "@local_roborio//:linux",
    "@local_roborio//:windows",
    "@local_raspi_32//:macos",
    "@local_raspi_32//:linux",
    "@local_raspi_32//:windows",
    "@local_bullseye_32//:macos",
    "@local_bullseye_32//:linux",
    "@local_bullseye_32//:windows",
    "@local_bullseye_64//:macos",
    "@local_bullseye_64//:linux",
    "@local_bullseye_64//:windows",
    "@local_bookworm_32//:macos",
    "@local_bookworm_32//:linux",
    "@local_bookworm_32//:windows",
    "@local_bookworm_64//:macos",
    "@local_bookworm_64//:linux",
    "@local_bookworm_64//:windows",
)

setup_legacy_setup_jdk_dependencies()

http_archive(
    name = "bzlmodrio-ni",
    sha256 = "197fceac88bf44fb8427d5e000b0083118d3346172dd2ad31eccf83a5e61b3ce",
    url = "https://github.com/wpilibsuite/bzlmodRio-ni/releases/download/2025.0.0/bzlmodRio-ni-2025.0.0.tar.gz",
)

load("@bzlmodrio-ni//:maven_cpp_deps.bzl", "setup_legacy_bzlmodrio_ni_cpp_dependencies")

setup_legacy_bzlmodrio_ni_cpp_dependencies()

http_archive(
    name = "bzlmodrio-opencv",
    sha256 = "4f4a607956ca8555618736c3058dd96e09d02df19e95088c1e352d2319fd70c7",
    url = "https://github.com/wpilibsuite/bzlmodRio-opencv/releases/download/2025.4.10.0-2/bzlmodRio-opencv-2025.4.10.0-2.tar.gz",
)

load("@bzlmodrio-opencv//:maven_cpp_deps.bzl", "setup_legacy_bzlmodrio_opencv_cpp_dependencies")

setup_legacy_bzlmodrio_opencv_cpp_dependencies()

load("@bzlmodrio-opencv//:maven_java_deps.bzl", "setup_legacy_bzlmodrio_opencv_java_dependencies")

setup_legacy_bzlmodrio_opencv_java_dependencies()

http_archive(
    name = "build_bazel_apple_support",
    sha256 = "c4bb2b7367c484382300aee75be598b92f847896fb31bbd22f3a2346adf66a80",
    url = "https://github.com/bazelbuild/apple_support/releases/download/1.15.1/apple_support.1.15.1.tar.gz",
)

load(
    "@build_bazel_apple_support//lib:repositories.bzl",
    "apple_support_dependencies",
)

apple_support_dependencies()

# Setup quickbuf compiler
QUICKBUF_VERSION = "1.3.2"

http_file(
    name = "quickbuffer_protoc_linux",
    executable = True,
    sha256 = "f9a041bccaa7040db523666ef1b5fe9f6f94e70a82c88951f18f58aadd9c50b5",
    url = "https://repo1.maven.org/maven2/us/hebi/quickbuf/protoc-gen-quickbuf/" + QUICKBUF_VERSION + "/protoc-gen-quickbuf-" + QUICKBUF_VERSION + "-linux-x86_64.exe",
)

http_file(
    name = "quickbuffer_protoc_osx",
    executable = True,
    sha256 = "ea307c2b69664ae7e7c69db4cddf5803187e5a34bceffd09a21652f0f16044f7",
    url = "https://repo1.maven.org/maven2/us/hebi/quickbuf/protoc-gen-quickbuf/" + QUICKBUF_VERSION + "/protoc-gen-quickbuf-" + QUICKBUF_VERSION + "-osx-x86_64.exe   ",
)

http_file(
    name = "quickbuffer_protoc_windows",
    executable = True,
    sha256 = "27dc1f29764a62b5e6a813a4bcd63e81bbdc3394da760a44acae1025b4a89f1d",
    url = "https://repo1.maven.org/maven2/us/hebi/quickbuf/protoc-gen-quickbuf/" + QUICKBUF_VERSION + "/protoc-gen-quickbuf-" + QUICKBUF_VERSION + "-windows-x86_64.exe ",
)

# Setup rules_proto
http_archive(
    name = "rules_proto",
    sha256 = "0e5c64a2599a6e26c6a03d6162242d231ecc0de219534c38cb4402171def21e8",
    strip_prefix = "rules_proto-7.0.2",
    url = "https://github.com/bazelbuild/rules_proto/releases/download/7.0.2/rules_proto-7.0.2.tar.gz",
)

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies")

rules_proto_dependencies()

load("@rules_proto//proto:setup.bzl", "rules_proto_setup")

rules_proto_setup()
