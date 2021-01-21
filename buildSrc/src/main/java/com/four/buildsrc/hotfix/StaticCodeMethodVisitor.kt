package com.four.buildsrc.hotfix

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

//用于注入静态字段
class StaticCodeMethodVisitor(
    private val owner: String,
    api: Int, methodVisitor: MethodVisitor,
    access: Int, name: String, descriptor: String
):
    AdviceAdapter(api, methodVisitor, access, name, descriptor) {

    override fun onMethodEnter() {
        super.onMethodEnter()
        mv.visitTypeInsn(NEW, "$owner${'$'}Companion")
        mv.visitInsn(DUP)
        mv.visitInsn(ACONST_NULL)
        mv.visitMethodInsn(
            INVOKESPECIAL,
            "$owner${'$'}Companion",
            "<init>",
            "(Lkotlin/jvm/internal/DefaultConstructorMarker;)V",
            false
        )
        mv.visitFieldInsn(
            PUTSTATIC,
            owner,
            "Companion",
            "L$owner${'$'}Companion;"
        )
        mv.visitInsn(RETURN)
        mv.visitMaxs(3, 0)
        mv.visitEnd()
    }
}