call runcrud.bat
if "%ERRORLEVEL%"=="0" goto openWebBrowser
echo.
echo Error in RUNCRUD

:openWebBrowser
start chrome http://localhost:8080/crud/v1/tasks
echo Work is finished.