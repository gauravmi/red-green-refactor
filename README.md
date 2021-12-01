# red-green-refactor
Intellij Idea plugin for visualisation of TDD process

## Prerequisites
- IntelliJ IDE
- JDK11

## How to run the plugin?
There are two methods of running the plugin:

1. **Opening a new separate IDE (version 2020.2.3) with the plugin already installed in it**

    - Open the Gradle tool window.
    - Go to `tdd`&rarr;`Tasks`&rarr;`intellij`
    - Double-click on the `runIde` task to execute it (if it’s not in the list, hit the Refresh button at the top of the Gradle window).

2. **Manually installing the plugin in your current IDE**  
   
    You first need to build the plugin and then install it in the IDE. Follow the below steps: 

    1. Build the plugin
        - Open the Gradle tool window.
        - Go to `tdd`&rarr;`Tasks`&rarr;`intellij`
        - Double-click on the `buildPlugin` task to build the plugin.
        - A zip file will be created at `build`&rarr;`distributions`&rarr;`tdd-1.0.4.zip`

    2. Install it in the IDE
        - Go to the `Plugins` tab in the `Preferences` of the IntelliJ IDE.
        - Click on the Settings icon.
        - Select the `Install Plugin from Disk...` option.
        - Now select the zip file for the plugin and restart the IDE.

## How to publish the plugin?

We need to set an Environment Variable(TOKEN) for publishing the plugin to JetBrains Marketplace. 

follow the below steps to set Environment Variable:

1. Go to `Run tab`&rarr; `Edit Configuration`&rarr; `Edit configuration templates..` 
2. In Environment Variables textbox enter -> `TDD_TOKEN={token}`
3. Run the `publishPlugin` task

## Important Links
- [Plugin Compatibility](https://jetbrains.org/intellij/sdk/docs/basics/getting_started/plugin_compatibility.html)
- [How to build a plugin that is compatible to multiple versions of IntelliJ ?](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360000620084-how-to-build-a-plugin-that-is-compatible-to-multiple-versions-of-IntelliJ-)
- [Build Number Ranges](https://jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html)
- [Publishing Plugins with Gradle](https://jetbrains.org/intellij/sdk/docs/tutorials/build_system/deployment.html)
