#!/bin/bash

# This script attempts to downgrade the Gradle and dependency versions of an Android project
# to a known compatible set for older Android Studio versions (like Iguana 2023.2.1).

# --- Configuration ---
# These are the versions that the script will set.
AGP_VERSION="8.3.1"
GRADLE_VERSION="8.4"
COMPILE_SDK_VERSION="34"
TARGET_SDK_VERSION="34"
COMPOSE_BOM_VERSION="2023.03.00"
CORE_KTX_VERSION="1.9.0"
ACTIVITY_COMPOSE_VERSION="1.7.0"
LIFECYCLE_RUNTIME_KTX_VERSION="2.6.1"

# --- File Paths ---
# The script assumes a standard Android project structure.
LIBS_VERSIONS_TOML="gradle/libs.versions.toml"
APP_BUILD_GRADLE="app/build.gradle.kts"
GRADLE_WRAPPER_PROPERTIES="gradle/wrapper/gradle-wrapper.properties"

# --- Functions ---

# Function to replace a version in a file.
# $1: file path
# $2: key
# $3: new version
replace_version() {
  local file_path="$1"
  local key="$2"
  local new_version="$3"

  if [ -f "$file_path" ]; then
    # This sed command looks for a line like 'key = "..."' and replaces the version.
    # It's designed to be flexible with whitespace around the '='.
    sed -i '' "s/^	*($key\s*=\s*)\"[^\"]*\"/\1\"$new_version\"/" "$file_path"
    echo "Updated $key to $new_version in $file_path"
  else
    echo "Warning: $file_path not found. Skipping."
  fi
}

replace_sdk_version() {
    local file_path="$1"
    local key="$2"
    local new_version="$3"

    if [ -f "$file_path" ]; then
        sed -i '' "s/^	*(\s*$key\s*=\s*)[0-9]*/\1$new_version/" "$file_path"
        echo "Updated $key to $new_version in $file_path"
    else
        echo "Warning: $file_path not found. Skipping."
    fi
}


# --- Main Script ---

echo "Starting project downgrade script..."
echo "IMPORTANT: This script will modify your project files. Make sure you have a backup or your project is under version control."
read -p "Do you want to continue? (y/n) " -n 1 -r
echo ""
if [[ ! $REPLY =~ ^[Yy]$ ]]; then
  echo "Aborting."
  exit 1
fi

# Update gradle/libs.versions.toml
if [ -f "$LIBS_VERSIONS_TOML" ]; then
    replace_version "$LIBS_VERSIONS_TOML" "agp" "$AGP_VERSION"
    replace_version "$LIBS_VERSIONS_TOML" "composeBom" "$COMPOSE_BOM_VERSION"
    replace_version "$LIBS_VERSIONS_TOML" "coreKtx" "$CORE_KTX_VERSION"
    replace_version "$LIBS_VERSIONS_TOML" "activityCompose" "$ACTIVITY_COMPOSE_VERSION"
    replace_version "$LIBS_VERSIONS_TOML" "lifecycleRuntimeKtx" "$LIFECYCLE_RUNTIME_KTX_VERSION"
else
    echo "Warning: $LIBS_VERSIONS_TOML not found. Skipping."
fi


# Update app/build.gradle.kts
if [ -f "$APP_BUILD_GRADLE" ]; then
    replace_sdk_version "$APP_BUILD_GRADLE" "compileSdk" "$COMPILE_SDK_VERSION"
    replace_sdk_version "$APP_BUILD_GRADLE" "targetSdk" "$TARGET_SDK_VERSION"
else
    echo "Warning: $APP_BUILD_GRADLE not found. Skipping."
fi


# Update gradle-wrapper.properties
if [ -f "$GRADLE_WRAPPER_PROPERTIES" ]; then
  sed -i '' "s/distributionUrl=.*/distributionUrl=https:\/\/services.gradle.org\/distributions\/gradle-$GRADLE_VERSION-bin.zip/" "$GRADLE_WRAPPER_PROPERTIES"
  echo "Updated Gradle version to $GRADLE_VERSION in $GRADLE_WRAPPER_PROPERTIES"
else
  echo "Warning: $GRADLE_WRAPPER_PROPERTIES not found. Skipping."
fi

echo "Script finished."
echo "Please run './gradlew --stop' and then sync your project with Gradle files in Android Studio."
