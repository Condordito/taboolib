package taboolib.common.platform.function

import taboolib.common.io.getInstance
import taboolib.common.io.runningClasses
import taboolib.common.platform.Platform
import taboolib.common.platform.PlatformFactory

/**
 * 搜索该类的当前平台实现
 */
@Suppress("UNCHECKED_CAST")
fun <T> Class<T>.findImplementation(): T? {
    return runningClasses.firstOrNull { isAssignableFrom(it) && it != this && Platform.check(it) }?.getInstance(true)?.get() as? T
}