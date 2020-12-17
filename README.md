# red-green-refactor
Intellij Idea plugin for visualisation of TDD process

## Prerequisites
- IntelliJ IDE
- JDK8

## How to run the plugin?
There are two methods of running the plugin:
- Opening a new IDE with the plugin already installed in it
- Manually installing the plugin in your current IDE

### How to run IntelliJ IDE with plugin?
- Open the Gradle tool window.
- Go to tdd -> Tasks -> intellij
- Double-click on the `runIde` task to execute it (if it’s not in the list, hit the Refresh button at the top of the Gradle window).

### How to manually install the plugin in the current IDE?
You first need to build the plugin and then install it in the IDE. FOllow the below steps: 

#### 1. Build the plugin
- Open the Gradle tool window.
- Go to tdd -> Tasks -> intellij
- Double-click on the `buildPlugin` task to build the plugin.
- A zip file will be created at `build > distributions > tdd-1.0.4.zip`

#### 2. Install it in the IDE
- Go to the Plugins tab in the Preferences of the IntelliJ IDE.
- Click on the Settings icon.
- Select the `Install Plugin from Disk...` option.
- Now select the zip file for the plugin and restart the IDE.

## Important Links
- [Plugin Compatibility](https://jetbrains.org/intellij/sdk/docs/basics/getting_started/plugin_compatibility.html)
- [How to build a plugin that is compatible to multiple versions of IntelliJ ?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360000620084-how-to-build-a-plugin-that-is-compatible-to-multiple-versions-of-IntelliJ-)
- [Build Number Ranges](https://jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html)
- [Publishing Plugins with Gradle](https://jetbrains.org/intellij/sdk/docs/tutorials/build_system/deployment.html)
