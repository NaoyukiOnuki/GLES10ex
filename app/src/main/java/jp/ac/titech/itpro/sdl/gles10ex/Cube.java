package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private ShortBuffer lbuf;
    private ShortBuffer rbuf;
    private ShortBuffer bbuf;
    private ShortBuffer tbuf;
    private ShortBuffer frontbuf;
    private ShortBuffer backbuf;
    private float x, y, z;

    public Cube(float s, float x, float y, float z) {
        float[] vertices = {
                -s, -s, -s,
                -s, -s, s,
                -s, s, -s,
                -s, s, s,
                s, -s, -s,
                s, -s, s,
                s, s, -s,
                s, s, s
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        short[] lindices = {
                0,1,3,2
        };
        lbuf = ByteBuffer.allocateDirect(lindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        lbuf.put(lindices);
        lbuf.position(0);
        short[] rindices = {
                4,5,7,6
        };
        rbuf = ByteBuffer.allocateDirect(rindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        rbuf.put(rindices);
        rbuf.position(0);
        short[] bindices = {
                0,1,5,4
        };
        bbuf = ByteBuffer.allocateDirect(bindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        bbuf.put(bindices);
        bbuf.position(0);
        short[] tindices = {
                2,3,7,6
        };
        tbuf = ByteBuffer.allocateDirect(tindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        tbuf.put(tindices);
        tbuf.position(0);
        short[] frontindices = {
                1,3,7,5
        };
        frontbuf = ByteBuffer.allocateDirect(frontindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        frontbuf.put(frontindices);
        frontbuf.position(0);
        short[] backindices = {
                0,2,6,4
        };
        backbuf = ByteBuffer.allocateDirect(backindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        backbuf.put(backindices);
        backbuf.position(0);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        gl.glNormal3f(-1,0,0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , lbuf.remaining() , GL10.GL_UNSIGNED_SHORT, lbuf);
        gl.glNormal3f(0,1,0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , tbuf.remaining() , GL10.GL_UNSIGNED_SHORT, tbuf);
        gl.glNormal3f(0,0,1);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , frontbuf.remaining() , GL10.GL_UNSIGNED_SHORT, frontbuf);
        gl.glNormal3f(0,-1,0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , bbuf.remaining() , GL10.GL_UNSIGNED_SHORT, bbuf);
        gl.glNormal3f(1,0,0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , rbuf.remaining() , GL10.GL_UNSIGNED_SHORT, rbuf);
        gl.glNormal3f(0,0,-1);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , backbuf.remaining() , GL10.GL_UNSIGNED_SHORT, backbuf);
    }
    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
