# TensorFlow Android stand-alone demo

Android demo source files extracted from original TensorFlow source. (TensorFlow r0.10)

To build this demo, you don't need to prepare build environment with Bazel, and it only requires AndroidStudio.

If you would like to build jni codes, only NDK is requied to build it.


## How to build jni codes
First install NDK, and set path for NDK tools, and then type commands below to create .so file.

    $ cd jni-build
    $ make
    $ make install


## Screenshot

Youtube: https://www.youtube.com/watch?v=Z7XcoigrEbA

![image](https://dl.dropboxusercontent.com/u/8527/Screenshot_20161019-170858.png)

