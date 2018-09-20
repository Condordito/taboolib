package me.skymc.taboolib.common.versioncontrol;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.stream.IntStream;

/**
 * 我不信 ClassNotFound 的邪，自己写了一个发现还是一样。。。
 *
 * @Author sky
 * @Since 2018-9-19 21:33
 */
public class SimpleMethodVisitor extends MethodVisitor {

    private final SimpleVersionControl simpleVersionControl;

    public SimpleMethodVisitor(SimpleVersionControl simpleVersionControl, MethodVisitor methodVisitor) {
        super(Opcodes.ASM6, methodVisitor);
        this.simpleVersionControl = simpleVersionControl;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        super.visitMethodInsn(opcode, translate(owner), name, translate(descriptor), isInterface);
    }

    @Override
    public void visitLdcInsn(Object value) {
        super.visitLdcInsn(value instanceof String ? translate((String) value) : value);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, translate(type));
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        super.visitFieldInsn(opcode, translate(owner), name, translate(descriptor));
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, translate(descriptor), translate(signature), start, end, index);
    }

    private String translate(String target) {
        return target == null ? null : target.replaceAll("/" + simpleVersionControl.getFrom() + "/", "/" + simpleVersionControl.getTo() + "/");
    }

    private String[] translate(String[] target) {
        if (target == null) {
            return target;
        }
        IntStream.range(0, target.length).forEach(i -> target[i] = translate(target[i]));
        return target;
    }
}