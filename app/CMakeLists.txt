# 需要获取更多关于CMake的知识请移步到: https://d.android.com/studio/projects/add-native-code.html

#根据你的需要设置要使用CMake的最低版本，版本之间包含的功能有区别
cmake_minimum_required(VERSION 3.4.1)

#创建一个库，三个参数意义：1：库名称；2：库类型，是static的还是shared的；3：要编译为库的源文件
#多次调用这个函数会生成多个库，而且Gradle会自动将Shared类型的库打包到Apk中
add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp )

# 如果C++有头文件，最好还是在这边声明一下路径
include_directories(src/main/cpp/include/)


#********************************NDK API  start**********************************
#Android 平台包含了很多预编译的库，CMake会搜索这些库所在的路径，所以你只需提供库名称CMake就可以找到这些库
#find_library 找到目标库（此处为log库）并申明一个变量（log-lib），将这个库的路径存储在这个变量中，以供其他地方使用
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

#为了使我们自己生成的原生库native-lib 可以调用NDK 中的log库的函数，需要将原生库链接到log库中
#参数意义：1：要目标库 2：find_library 中找到的库
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library included in the NDK.
                       ${log-lib} )

#我们不仅可以通过上面的方法使用NDK中编译好的库，也可以使用NDK中的源代码文件
#add_library( app-glue

             #STATIC

             #${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c )
# You need to link static libraries against your shared native library.
#target_link_libraries( native-lib app-glue ${log-lib} )
#********************************NDK API end**********************************

#********************************使用第三方库 start**********************************
add_library( #给导入的库定义一个变量，以便下面函数调用
             imported-lib
             SHARED
             IMPORTED )
set_target_properties( # Specifies the target library.
                       imported-lib

                       # Specifies the parameter you want to define.
                       PROPERTIES IMPORTED_LOCATION

                       # Provides the path to the library you want to import.
                       src/main/jniLibs/${ANDROID_ABI}/my-lib.so )
#********************************使用第三方库 end**********************************