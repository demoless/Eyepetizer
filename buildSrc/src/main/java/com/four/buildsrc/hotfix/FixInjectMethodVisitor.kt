package com.four.buildsrc.hotfix

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

//用于热修注入字段及热修执行逻辑
class FixInjectMethodVisitor(
    private val owner: String,
    private val fileName: String,
    api: Int, methodVisitor: MethodVisitor,
    access: Int, name: String, private val descriptor: String
)
    : AdviceAdapter(api, methodVisitor, access, name, descriptor) {

    private companion object {
        private const val METHOD_FIX_ANNOTATION = "Lcom/ds/hotfix/FixModifier;"
        private const val METHOD_ADD_ANNOTATION = "Lcom/ds/hotfix/FixAdd;"
    }

    override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
        if (descriptor == METHOD_FIX_ANNOTATION || descriptor == METHOD_ADD_ANNOTATION) {
            ClassInfo.classSets.add(this.descriptor)
        }
        return super.visitAnnotation(descriptor, visible)
    }

    override fun onMethodEnter() {
        super.onMethodEnter()
        println("------------changeQuickRedirect 方法内判空执行----------------------")
        val label0 = Label()
        mv.visitLabel(label0)
        mv.visitFieldInsn(
            GETSTATIC,
            owner,
            "changeQuickRedirect",
            "Lcom/ds/hotfix/ChangeQuickRedirect;"
        )
        val label1 = Label()
        mv.visitJumpInsn(IFNULL, label1)
        val label2 = Label()
        mv.visitLabel(label2)
        mv.visitFieldInsn(
            GETSTATIC,
            owner,
            "changeQuickRedirect",
            "Lcom/ds/hotfix/ChangeQuickRedirect;"
        )
        mv.visitLdcInsn("test")
        mv.visitVarInsn(ALOAD, 0)
        mv.visitMethodInsn(
            INVOKEINTERFACE,
            "com/ds/hotfix/ChangeQuickRedirect",
            "isSupport",
            "(Ljava/lang/String;Ljava/lang/Object;)Z",
            true
        )
        mv.visitJumpInsn(IFEQ, label1)
        val label3 = Label()
        mv.visitLabel(label3)
        mv.visitFieldInsn(
            GETSTATIC,
            owner,
            "changeQuickRedirect",
            "Lcom/ds/hotfix/ChangeQuickRedirect;"
        )
        mv.visitLdcInsn("test")
        mv.visitInsn(ACONST_NULL)
        mv.visitMethodInsn(
            INVOKEINTERFACE,
            "com/ds/hotfix/ChangeQuickRedirect",
            "accessDispatch",
            "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;",
            true
        )
        mv.visitInsn(POP)
        val label4 = Label()
        mv.visitLabel(label4)
        mv.visitLdcInsn("as")
        mv.visitLdcInsn("fix invoke!")
        mv.visitMethodInsn(
            INVOKESTATIC,
            "android/util/Log",
            "e",
            "(Ljava/lang/String;Ljava/lang/String;)I",
            false
        )
        mv.visitInsn(POP)
        mv.visitLabel(label1)
        mv.visitFrame(F_SAME, 0, null, 0, null)
        mv.visitInsn(RETURN)
        val label5 = Label()
        mv.visitLabel(label5)
        mv.visitLocalVariable("this", "L$owner;", null, label0, label5, 0)
        mv.visitMaxs(3, 1)
        mv.visitEnd()
    }

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
    }
}