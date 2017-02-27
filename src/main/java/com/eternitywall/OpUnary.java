package com.eternitywall;

/**
 * Operations that act on a single message.
 * @extends com.eternitywall.Op
 */
class OpUnary extends Op {

    byte[] arg;

    @Override
    public String _TAG_NAME() {
        return "";
    }

    OpUnary() {
        super();
        this.arg = new byte[]{};
    }
    OpUnary(byte[] arg_) {
        super();
        this.arg = arg_;
    }

    public static Op deserializeFromTag(StreamDeserializationContext ctx, byte tag) {
        if (tag == OpAppend._TAG){
            return new OpAppend();
        }else if (tag == OpPrepend._TAG){
            return new OpPrepend();
        }else if (tag == OpSHA1._TAG){
            return new OpSHA1();
        }else if (tag == OpSHA256._TAG){
            return new OpSHA256();
        }else if (tag == OpRIPEMD160._TAG){
            return new OpRIPEMD160();
        }else {
            System.err.print("Unknown operation tag: " + tag);
            return null;
        }
    }
    @Override
    public void serialize(StreamSerializationContext ctx) {
        super.serialize(ctx);
        ctx.writeVarbytes(this.arg);
    }
    @Override
    public String toString() {
        return this._TAG_NAME() + ' ' + Utils.bytesToHex(this.arg);
    }
}