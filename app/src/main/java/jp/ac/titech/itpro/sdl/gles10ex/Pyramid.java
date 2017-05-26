package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Pyramid implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private FloatBuffer nbuf;
    private ShortBuffer bottombuf;
    private ShortBuffer topbuf;
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
        float[] normals = {
                // bottom
                -1, -1, -1,
                1, -1, -1,
                -1, -1, 1,
                1, -1, 1,
                // top
                0, 1, 0,
        };
        nbuf = ByteBuffer.allocateDirect(normals.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        nbuf.put(normals);
        nbuf.position(0);
        short[] bottomindices = {
                0,1,3,2
        };
        bottombuf = ByteBuffer.allocateDirect(bottomindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        bottombuf.put(bottomindices);
        bottombuf.position(0);
        short[] topindices = {
                4,0,1,3,2
        };
        topbuf = ByteBuffer.allocateDirect(topindices.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        topbuf.put(topindices);
        topbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY );
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        gl.glNormalPointer( GL10.GL_FLOAT , 0 , nbuf );

        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , bottombuf.remaining() , GL10.GL_UNSIGNED_SHORT, bottombuf);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , topbuf.remaining() , GL10.GL_UNSIGNED_SHORT, topbuf);
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
