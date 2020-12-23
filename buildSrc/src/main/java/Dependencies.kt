import sun.misc.Version

object Sdk {
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val COMPILE_SDK_VERSION = 29
}

object Versions {
    const val ANDROID_MATERIAL = "1.1.0"
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val APPCOMPAT = "1.1.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.36.0"
    const val MOTION_LAYOUT = "2.0.0-beta4"
    const val CARD_VIEW = "1.0.0"
}

object BuildPluginsVersion {
    const val AGP = "3.6.3"
    const val DETEKT = "1.8.0"
    const val KOTLIN = "1.3.72"
    const val KTLINT = "9.2.1"
    const val VERSIONS_PLUGIN = "0.28.0"
}

object SupportLibs {
    const val CARD_VIEW = "androidx.cardview:cardview:${Versions.CARD_VIEW}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "com.android.support.constraint:constraint-layout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val MOTION_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.MOTION_LAYOUT}"

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
