@echo on
rem you could also remove the line above, because it might help you to see what happens

rem /i option is needed to avoid the batch file asking you whether destination folder is a file or a folder
rem /e option is needed to copy also all folders and subfolders
xcopy /S /Q /Y /F "D:\WORK\Android\Project\AutoTouchPro\app\release\release\app-release.apk" "D:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\app-release.apk*" /i /e
xcopy /S /Q /Y /F "D:\WORK\Android\Project\AutoTouchPro\app\release\release\output-metadata.json" "D:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\output-metadata.json*" /i /e
xcopy /Q /Y /F "D:\WORK\Android\Project\AutoTouchPro\new_release_note.txt" "D:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\new_release_note.txt" /i /e

git add -A
git status
git commit -m "New release"
git config --global credential.helper store
git push

pause