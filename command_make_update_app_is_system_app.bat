adb push privapp-permissions-attp-updater.xml /sdcard/privapp-permissions-attp-updater.xml
adb push ATTP_updater.apk /sdcard/ATTP_updater.apk
adb push "gif.exe" "/data/local/tmp/gif.exe"
adb push "libc++_shared.so" "/data/local/tmp/libc++_shared.so"
adb push "libopencv_java4.so" "/data/local/tmp/libopencv_java4.so"

adb shell "su -c 'mount -o rw,remount /'"
adb shell "su -c 'mount -o rw,remount /system'"
adb shell "su -c 'mount -o rw,remount /etc'"
adb shell "su -c 'chmod 755 /etc'"
adb shell "su -c 'chmod 755 /etc/permissions'"
adb shell "su -c 'mv /sdcard/privapp-permissions-attp-updater.xml /etc/permissions'"
adb shell "su -c 'chmod 644 /etc/permissions/privapp-permissions-attp-updater.xml'"
adb shell "su -c 'chmod 755 /system/priv-app/'"
adb shell "su -c 'mv /sdcard/ATTP_updater.apk /system/priv-app'"
adb shell "su -c 'chmod 644 /system/priv-app/ATTP_updater.apk'"
adb reboot
pause