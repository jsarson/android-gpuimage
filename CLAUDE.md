# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the Android GPUImage library - a port of the iOS GPUImage framework for real-time GPU-accelerated image and video filtering on Android. The library provides 60+ image filters using OpenGL ES 2.0 shaders for high-performance image processing.

## Build Commands

### Basic Build
```bash
./gradlew clean assemble
```

### Library Only
```bash
./gradlew :library:assemble
```

### Publishing (Maven)
```bash
./gradlew :library:publishToMavenLocal
```

### Sample App
The sample app is currently disabled in settings.gradle but can be re-enabled by uncommenting:
```bash
# Uncomment in settings.gradle: include ':sample'
./gradlew :sample:assembleDebug
```

## Architecture

### Core Components

1. **GPUImage.java** - Main API class providing high-level interface for image filtering
2. **GPUImageView.java** - Custom view component that wraps GLSurfaceView for easy integration
3. **GPUImageRenderer.java** - OpenGL ES renderer handling the actual GPU processing
4. **Filter System** - Located in `library/src/main/java/jp/co/cyberagent/android/gpuimage/filter/`

### Filter Architecture
- **GPUImageFilter.java** - Base class for all filters, handles shader compilation and OpenGL state
- **GPUImageTwoInputFilter.java** - Base for filters requiring two input textures (blend modes)
- **GPUImageTwoPassFilter.java** - Base for filters requiring multiple rendering passes
- **GPUImageFilterGroup.kt** - Allows combining multiple filters in sequence

### Native Components
- **YUV Decoder**: Native C library (`library/src/main/cpp/yuv-decoder.c`) for efficient camera frame processing
- **CMake Build**: Native library built with CMake, links against OpenGL ES 2.0 and NDK graphics libraries

## Key Technical Details

### OpenGL Integration
- Requires OpenGL ES 2.0 minimum (Android 2.2+)
- Uses GLSurfaceView and TextureView for rendering
- Custom shader loading and compilation system in `util/OpenGlUtils.java`

### Camera Integration
- Supports both Camera1 and Camera2 APIs
- Real-time preview filtering with frame callbacks
- Automatic orientation and rotation handling

### Memory Management
- GPU texture management for efficient memory usage
- Bitmap recycling patterns for large image processing
- Async processing for non-blocking UI operations

### Filter Categories
- **Color Adjustments**: Brightness, contrast, saturation, hue, gamma
- **Blur Effects**: Gaussian, box, bilateral, zoom blur
- **Blend Modes**: 20+ Photoshop-style blend modes (multiply, overlay, screen, etc.)
- **Distortion**: Swirl, bulge, sphere refraction, glass sphere
- **Edge Detection**: Sobel, threshold, directional edge detection
- **Artistic**: Sketch, toon, crosshatch, halftone, posterize

## Development Guidelines

### Adding New Filters
1. Extend `GPUImageFilter` or appropriate base class
2. Implement vertex/fragment shaders following existing patterns
3. Handle uniform parameters in `onInit()` and setter methods
4. Add to filter catalog in sample app's `GPUImageFilterTools.kt`

### Shader Development  
- Vertex and fragment shaders stored as static strings in filter classes
- Follow GLSL ES 2.0 syntax
- Use consistent attribute/uniform naming conventions
- Test on multiple devices for compatibility

### Build System
- Uses Gradle with version catalogs (`gradle/libs.versions.toml`)
- Kotlin DSL not used - traditional Groovy build scripts
- NDK integration via CMake for native components
- Maven publishing configured for library distribution

## Testing
The project does not include automated tests. Testing is primarily done through the sample application which demonstrates all filter functionality with both camera and gallery images.

## Dependencies
- Minimal external dependencies - primarily Android SDK and NDK
- No third-party image processing libraries
- Uses native OpenGL ES and Android graphics APIs