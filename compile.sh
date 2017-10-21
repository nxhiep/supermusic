echo "Begin"
echo "copy GWT.xml file"
root="/Users/hiep/git"
project="$root/SuperMusic/Cordova"
dropbox="$root/SuperMusic/Output"
gwtProject="$root/SuperMusic"

#ant -f build.xml

#echo "removing previous compiled folder"
#rm -rf $project/www/supermusic/*
#rm -rf $project/www/css/*
#rm -rf $project/www/images/*
#rm -rf $project/www/fonts/*
#rm -rf $project/www/js/*

#rm -rf $project/platforms/ios/www/supermusic/*
#rm -rf $project/platforms/ios/www/css/*
#rm -rf $project/platforms/ios/www/images/*
#rm -rf $project/platforms/ios/www/fonts/*
#rm -rf $project/platforms/ios/www/js/*

#rm -rf $project/platforms/android/assets/www/supermusic/*
#rm -rf $project/platforms/android/assets/www/css/*
#rm -rf $project/platforms/android/assets/www/images/*
#rm -rf $project/platforms/android/assets/www/fonts/*
#rm -rf $project/platforms/android/assets/www/js/*

echo "copying newly compiled folder"
cp -r $gwtProject/war/supermusic/* $project/www/supermusic/
cp -r $gwtProject/war/css/* $project/www/css/
cp -r $gwtProject/war/images/* $project/www/images/
cp -r $gwtProject/war/images/* $project/www/fonts/
cp -r $gwtProject/war/images/* $project/www/js/

cp -r $gwtProject/war/supermusic/* $project/platforms/ios/www/supermusic/
cp -r $gwtProject/war/css/* $project/platforms/ios/www/css/
cp -r $gwtProject/war/images/* $project/platforms/ios/www/images/
cp -r $gwtProject/war/fonts/* $project/platforms/ios/www/fonts/
cp -r $gwtProject/war/js/* $project/platforms/ios/www/js/

cp -r $gwtProject/war/supermusic/* $project/platforms/android/assets/www/supermusic/
cp -r $gwtProject/war/css/* $project/platforms/android/assets/www/css/
cp -r $gwtProject/war/images/* $project/platforms/android/assets/www/images/
cp -r $gwtProject/war/fonts/* $project/platforms/android/assets/www/fonts/
cp -r $gwtProject/war/js/* $project/platforms/android/assets/www/js/

rm -rf $aproject/littlezeros/gwt/*

cd $project

cordova build ios
cordova build android --release

adb uninstall com.hiep.supermusic
#adb install $project/platforms/android/build/outputs/apk/android-release.apk

#rm -rf $dropbox/littlezeros.apk
#cp -r $project/platforms/android/build/outputs/apk/android-release.apk $dropbox/littlezeros.apk

#rm -rf $gwtProject/war/lize/*

#appcfg.sh -A littlezeros-1051 -V v2 --enable_jar_splitting update $gwtProject/war/

echo "Done! enjoy result ^^"
