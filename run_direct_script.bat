::soạn thảo file script với đuôi atp xong chuột phải openwwith run_direct_script.bat -> chọn alway , sau chạy script vừa soạn xong chỉ cần click dup vào file atp

adb push %~n1%~x1 /sdcard/AutoTouchPro
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es command "stop"
adb shell am start-service -n com.tzappstudio.autotouchpro/.services.task.RunService --es file "%~n1%~x1"