@echo on
rem you could also remove the line above, because it might help you to see what happens

rem /i option is needed to avoid the batch file asking you whether destination folder is a file or a folder
rem /e option is needed to copy also all folders and subfolders
xcopy /S /Q /Y /F "E:\WORK\Android\Project\AutoTouchPro\app\README.md" "E:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\README.md*" /i
xcopy /S /Q /Y /F "E:\WORK\Android\Project\AutoTouchPro\app\release\release\app-release.apk" "E:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\app-release.apk*" /i
xcopy /S /Q /Y /F "E:\WORK\Android\Project\AutoTouchPro\app\release\release\output-metadata.json" "E:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\output-metadata.json*" /i
xcopy /Q /Y /F "E:\WORK\Android\Project\AutoTouchPro\new_release_note.txt" "E:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\new_release_note.txt" /i
xcopy /S /Q /Y /F "E:\WORK\Android\Project\AutoTouchProUpdater\app\release\app-release.apk" "E:\WORK\Android\Project\AutoTouchProUpdateRelease\AutoTouchPro\ATTP_updater.apk*" /i /e

git add -A
::git reset -- assets/*
git status
git commit -m "New release"
git config --global credential.helper store
git push origin main

pause