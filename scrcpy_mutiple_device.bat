setlocal ENABLEDELAYEDEXPANSION
set d=
FOR /F "tokens=* USEBACKQ" %%F IN (`adb devices`) DO (
	::lấy danh sách device dạng: "adb devices"
	set "d=%%F"
	::set d=!d!,%%F 
	:: replace chữ device thành -S để tắt màn hình device
	call set "result=%%d:device=%%"
	start cmd /C "scrcpy -s !result! -S"
)
	::echo %result%

pause