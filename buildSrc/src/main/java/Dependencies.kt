object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.36.0"
    const val VIEWPAGER2 = "1.0.0-alpha01"
    const val ANDROID_MATERIAL = "1.2.0-alpha02"
    const val LEGACY = "1.0.0"
    const val CAMERAX = "1.0.0-alpha03"
    const val CAMERAX_EXTENSIONS = "1.0.0-alpha03"
    const val CAMERAX_VIEW = "1.0.0-alpha03"
    const val PICASSO_VERSION = "2.71828"
    const val GLIDE = "4.10.0"

    const val CIRCLE_IMAGE = "3.1.0"
    const val SHIMMER = "0.4.0"
}

object BuildPluginsVersion {
    const val AGP = "3.6.3"
    const val DETEKT = "1.8.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"

}

object SupportLibs {
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val ANDROIDX_VIEWPAGER2 = "androidx.viewpager2:viewpager2:${Versions.VIEWPAGER2}"
    const val GOOGLE_ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val ANDROIDX_LEGACY =  "androidx.legacy:legacy-support-v4:${Versions.LEGACY}"
    const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGE}"
    const val FACEBOOK_SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER}"
    const val CAMERAX = "androidx.camera:camera-core:${Versions.CAMERAX}"
    const val CAMERAX_EXTENSIONS = "androidx.camera:camera-extensions:${Versions.CAMERAX_EXTENSIONS}"
    const val CAMERAX_VIEW = "androidx.camera:camera-view:${Versions.CAMERAX_VIEW}"
    const val PICASSO = "com.squareup.picasso:picasso:${Versions.PICASSO_VERSION}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}
