package taboolib.module.lang

import taboolib.common.platform.ProxyCommandSender
import taboolib.common.util.replaceWithOrder

/**
 * TabooLib
 * taboolib.module.lang.TypeText
 *
 * @author sky
 * @since 2021/6/20 10:55 下午
 */
class TypeText : Type {

    var text: String? = null

    constructor()
    constructor(text: String) {
        if (text.isNotEmpty()) {
            this.text = text
        }
    }

    fun asText(sender: ProxyCommandSender, def: String? = null, vararg args: Any): String? {
        return text?.replaceWithOrder(*args)?.translate(sender) ?: def
    }

    override fun init(source: Map<String, Any>) {
        text = source["text"]?.toString()
        // if blocked
        if (text?.isEmpty() == true) {
            text = null
        }
    }

    override fun send(sender: ProxyCommandSender, vararg args: Any) {
        if (text != null) {
            sender.sendMessage(text!!.replaceWithOrder(*args).translate(sender))
        }
    }

    override fun toString(): String {
        return "NodeText(text=$text)"
    }
}