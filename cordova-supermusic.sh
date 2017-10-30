cd ../
export PATH=/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/bin:"$PATH"
cordova create Cordova com.hiep.supermusic SuperMusic
cd Cordova
cordova platform add android
cordova platform add ios
cordova build
cordova plugin add org.apache.cordova.console
cordova plugin add org.apache.cordova.device
cordova plugin add cordova-plugin-media
cordova plugin add https://github.com/EddyVerbruggen/Toast-PhoneGap-Plugin.git
cordova plugin add https://git-wip-us.apache.org/repos/asf/cordova-plugin-vibration.git
cordova plugin add https://git-wip-us.apache.org/repos/asf/cordova-plugin-network-information.git
cordova plugin add https://github.com/brodysoft/Cordova-SQLitePlugin.git
cordova plugin add https://github.com/phonegap-build/PushPlugin.git
cordova plugin add https://github.com/apache/cordova-plugin-file.git
cordova plugin add https://github.com/apache/cordova-plugin-file-transfer.git
cordova plugin add https://github.com/apache/cordova-plugin-splashscreen.git
cordova plugin add https://github.com/katzer/cordova-plugin-local-notifications.git
