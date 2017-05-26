package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pyramid implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private ShortBuffer bottombuf;
    private ShortBuffer leftbuf;
    private ShortBuffer rightbuf;
    private ShortBuffer frontbuf;
    private ShortBuffer backbuf;
    private float x, y, z;

    public Pyramid(float s, float x, float y, float z) {
        float[] vertices = {
                // bottom
                -s, 0, -s,
                s, 0, -s,
                -s, 0, s,
                s, 0, s,
                // top
                0, s, 0,
        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        short[] bottomindices = {
                0,1,3,2
        };
        bottombuf = ByteBuffer.allocateDirect(bottomindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        bottombuf.put(bottomindices);
        bottombuf.position(0);
        short[] leftindices = {
                4,0,1
        };
        leftbuf = ByteBuffer.allocateDirect(leftindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        leftbuf.put(leftindices);
        leftbuf.position(0);
        short[] rightindices = {
                4,2,3
        };
        rightbuf = ByteBuffer.allocateDirect(rightindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        rightbuf.put(rightindices);
        rightbuf.position(0);
        short[] frontindices = {
                4,0,2
        };
        frontbuf = ByteBuffer.allocateDirect(frontindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        frontbuf.put(frontindices);
        frontbuf.position(0);
        short[] backindices = {
                4,1,3
        };
        backbuf = ByteBuffer.allocateDirect(backindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        backbuf.put(backindices);
        backbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glEnableClientState(GL10.GL_NORMAL_ARRAY );
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        //gl.glNormalPointer( GL10.GL_FLOAT , 0 , nbuf );

        gl.glNormal3f(0, -1, 0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , bottombuf.remaining() , GL10.GL_UNSIGNED_SHORT, bottombuf);
        gl.glNormal3f(0, 1, -1);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , leftbuf.remaining() , GL10.GL_UNSIGNED_SHORT, leftbuf);
        gl.glNormal3f(0, 1, 1);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , rightbuf.remaining() , GL10.GL_UNSIGNED_SHORT, rightbuf);
        gl.glNormal3f(-1, 1, 0);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , frontbuf.remaining() , GL10.GL_UNSIGNED_SHORT, frontbuf);
        gl.glNormal3f(1, 1, 0);
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
