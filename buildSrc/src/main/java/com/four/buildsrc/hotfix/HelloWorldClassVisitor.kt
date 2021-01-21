package com.four.buildsrc.hotfix

import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

class HelloWorldClassVisitor(private val classWriter: ClassWriter): ClassVisitor(Opcodes.ASM8, classWriter){

    private var isFieldExist: Boolean = false

    override fun visitMethod(
        access: Int,
        name: String,
        descriptor: String,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        println("name:$name --- descriptor: $descriptor")
        if(!name.equals("<clinit>")) {
            val methodVisitor = classWriter.visitMethod(access, name, descriptor, signature, exceptions)
            return HelloWorldMethodVisitor(api,methodVisitor,access, name, descriptor)
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    override fun visitField(
        access: Int,
        name: String,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        if (name.equals("changeQuickRedirect")) {
            isFieldExist = true
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    //为避免属性重复添加 在此执行属性添加逻辑
    override fun visitEnd() {
        super.visitEnd()
        if (!isFieldExist) {
            val fieldVisitor = cv.visitField(Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC,
                "changeQuickRedirect", "Lcom/ds/hotfix/ChangeQuickRedirect;",
                null, null)
            val annotationVisitor = fieldVisitor.visitAnnotation(
                "Lorg/jetbrains/annotations/Nullable;", false)
            annotationVisitor.visitEnd()
            fieldVisitor.visitEnd()
            isFieldExist = true
            println("------------changeQuickRedirect 字段注入完成----------------------")
        }
    }

    class HelloWorldMethodVisitor(api: Int,
                                  methodVisitor: MethodVisitor,
                                  access: Int, name: String,
                                  descriptor: String)
        : AdviceAdapter(api, methodVisitor, access, name, descriptor) {

        //扫描进入方法时执行
        override fun onMethodEnter() {
            super.onMethodEnter()
            println("-----------------HelloWorldClassVisitor onMethodEnter----------------")
            mv.visitLdcInsn("as")
            mv.visitLdcInsn("hello world!")
            mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false)
            //mv.visitInsn(Opcodes.POP)
        }

    }
}