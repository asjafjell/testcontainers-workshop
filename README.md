# Testcontainers workshop

This workshop aims to introduce the concept of testcontainers in code. 

**Citation from the Testcontainers website**
_Testcontainers is an open source framework for provisioning throwaway, on-demand containers for development and testing use cases._

## The goal of this workshop
Give you a rolling start on the concept of a Testcontainer. It just scratches the surface of what you can do, but it will hopefully give you enough insights to start experimenting on your own.


## The parts
1. Development environment setup
1. Verify by running the app
1. Set up whats needed for a Testcontainer setup
1. Run your first Testcontainer
1. Use the container in a test with a database

### 1. Development environment

Install Homebrew
```shell 
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Then install Maven
```shell
brew install maven
```

**Unlock Mac with the Priveleges app before next step**

We will need to propertly manage our Java environment. Jenv does that pretty well:
```shell
brew install jenv
```

Do as you are asked by Jenv, add the following lines to `~/.zshrc`:
```shell
  export PATH="$HOME/.jenv/bin:$PATH"
  eval "$(jenv init -)"
```

We need Java 21, using the Temurin Project
```shell
brew install temurin@21
```

Add Java 21 to your jenv
```shell
jenv add /Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home
```

Make Maven respect the Jenv plugin
```shell
jenv enable-plugin maven
jenv enable-plugin export
```

Set Java 21 as current local Java version
```shell
jenv local 21
```
