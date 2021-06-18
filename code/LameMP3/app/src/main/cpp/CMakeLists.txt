# CMakeLists.txt 内容如下：
# Sets the minimum version of CMake required to build your native library.
# This ensures that a certain set of CMake features is available to
# your build.

cmake_minimum_required(VERSION 3.10.2)

#project("myapplication")

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.

aux_source_directory(libmp3lame LAME_SRC_DIR)

add_library( # Specifies the name of the library.
			 mp3Lame

			 # Sets the library as a shared library.
			 SHARED

			 # Provides a relative path to your source file(s).
			${LAME_SRC_DIR}
			mp3_encoder.cpp
			lame_use.cpp
		)
			 
# Specifies a path to native header files.
# include_directories(src/main/cpp/include/)

find_library( # Sets the name of the path variable.
		log-lib

		# Specifies the name of the NDK library that
		# you want CMake to locate.
		log )

target_link_libraries( # Specifies the target library.
		mp3Lame

		# Links the target library to the log library
		# included in the NDK.
		${log-lib} )


# 如果要在CMakeList.txt 中添加NDK API, 或者其他已经存在的库，不是本文关注的重点，可参考： https://developer.android.com/studio/projects/add-native-code?hl=zh-cn#existing-project
