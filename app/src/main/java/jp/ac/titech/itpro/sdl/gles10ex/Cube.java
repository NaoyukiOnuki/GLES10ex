package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private FloatBuffer nbuf;
    private ShortBuffer ibuf1;
    private ShortBuffer ibuf2;
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
        float[] normals = {
                -s, -s, -s,
                -s, -s, s,
                -s, s, -s,
                -s, s, s,
                s, -s, -s,
                s, -s, s,
                s, s, -s,
                s, s, s
        };
        nbuf = ByteBuffer.allocateDirect(normals.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        nbuf.put(normals);
        nbuf.position(0);
        short[] indices1 = {
                0,1,3,2,6,4,5,1
        };
        ibuf1 = ByteBuffer.allocateDirect(indices1.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        ibuf1.put(indices1);
        ibuf1.position(0);
        short[] induces2 = {
                7,1,3,2,6,4,5,1
        };
        ibuf2 = ByteBuffer.allocateDirect(induces2.length * Short.SIZE)
                .order(ByteOrder.nativeOrder()).asShortBuffer();
        ibuf2.put(induces2);
        ibuf2.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY );
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);
        gl.glNormalPointer( GL10.GL_FLOAT , 0 , nbuf );

        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , ibuf1.remaining() , GL10.GL_UNSIGNED_SHORT, ibuf1);
        gl.glDrawElements( GL10.GL_TRIANGLE_FAN , ibuf2.remaining() , GL10.GL_UNSIGNED_SHORT, ibuf2);
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
