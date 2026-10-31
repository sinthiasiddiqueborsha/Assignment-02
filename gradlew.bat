@echo off
setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

set JAVA_EXE=
if not "%JAVA_HOME%" == "" (
    set JAVA_HOME=%JAVA_HOME:"=%
    set JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

if exist "%JAVA_EXE%" goto init

set JAVA_EXE=java.exe
"%JAVA_EXE%" -version >NUL 2>&1
if errorlevel 1 (
    echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
    goto fail
)

:init
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
set EXIT_CODE=%ERRORLEVEL%
goto exit

:fail
set EXIT_CODE=1

:exit
if "%OS%" == "Windows_NT" endlocal
exit /b %EXIT_CODE%
