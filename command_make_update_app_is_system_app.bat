adb push ATTP_updater.apk /sdcard/ATTP_updater.apk
adb shell "su -c 'mount -o rw,remount /'"
adb shell "su -c 'mount -o rw,remount /system'"
adb shell "su -c 'chmod 755 /system/priv-app/'"
adb shell "su -c 'mv /sdcard/ATTP_updater.apk /system/priv-app'"
adb shell "su -c 'chmod 644 /system/priv-app/ATTP_updater.apk'"
adb reboot
pause
