name := "server"

version := "0.1"

scalaVersion := "2.13.8"  // Scala é opcional, mas o SBT precisa dessa definição

Compile / javaSource := baseDirectory.value / "src" / "main" / "java"

libraryDependencies += "com.google.protobuf" % "protobuf-java" % "4.29.0"
