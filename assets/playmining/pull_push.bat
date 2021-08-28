adb pull /sdcard/AutoTouchPro/data.csv data_result.csv
'Nhan phim ba ki de ghi de data.csv'
pause
adb push data.csv /sdcard/AutoTouchPro
adb push ref.txt /sdcard/AutoTouchPro
adb push config.json /sdcard/AutoTouchPro
pause