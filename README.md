# Todo App

This is the simplest TODO app you will ever find. This application is written in Java with the SWT (_Standard Widget Toolkit_) GUI toolkit.

## Building

This project uses [Maven](https://maven.apache.org/). In order to build the JAVA package (FAT JAR file), please run:

``` shell
$ mvn clean compile package
```

## Packaging

In order to build the native package for your current operating system, please run the following command, after running the precedent Maven command.

``` shell
$ jpackage --input target/ --main-jar todo-0.1.jar --main-class todo.TodoApp --name TodoApp --type app-image
```

## License

This project is part of the public domain. See the [LICENSE](./LICEN[CS]E) file.
