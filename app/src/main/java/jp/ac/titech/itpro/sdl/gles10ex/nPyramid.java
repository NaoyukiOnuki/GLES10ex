package jp.ac.titech.itpro.sdl.gles10ex;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by onuki on 2017/05/26.
 */

public class nPyramid implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x, y, z;
    private int n;
    private ArrayList<Vector> normalVectorList = new ArrayList<>();

    private class MyPoint {
        final float x;
        final float y;
        final float z;
        MyPoint (float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        void addInto(ArrayList<Float> list) {
            list.add(x);
            list.add(y);
            list.add(z);
        }
    }
    private class Vector {
        final float x;
        final float y;
        final float z;
        Vector (float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        // 外積
        Vector crossProduct(Vector v) {
            return new Vector(y*v.z-z*v.y, z*v.x-x*v.z, x*v.y-y*v.x);
        }
        // 単位ベクトル
        Vector unit() {
            float length = (float) Math.sqrt(x*x+y*y+z*z);
            return new Vector(x / length, y / length, z / length);
        }
    }
    public nPyramid(int n, float s, float x, float y, float z) {
        ArrayList<MyPoint> bottoms = new ArrayList<>();
        // bottom
        for (int i = 0; i < n; i++) {
            double angle = Math.PI*2 * i / n;
            MyPoint p = new MyPoint(s * (float) Math.cos(angle), 0, s * (float) Math.sin(angle));
            bottoms.add(p);
        }
        MyPoint top = new MyPoint(0,s,0);

        ArrayList<Float> verticeList = new ArrayList<>();
        for (MyPoint p : bottoms) {
            p.addInto(verticeList);
        }
        for (int i = 0, j = 1; i < n; i++, j++) {
            if (j == n) j = 0;
            MyPoint bottom1 = bottoms.get(i);
            MyPoint bottom2 = bottoms.get(j);
            top.addInto(verticeList);
            bottom1.addInto(verticeList);
            bottom2.addInto(verticeList);

            // calculate normal vector
            Vector v1 = new Vector(top.x - bottom1.x, top.y - bottom1.y, top.z - bottom1.z);
            Vector v2 = new Vector(top.x - bottom2.x, top.y - bottom2.y, top.z - bottom2.z);
            Vector normal = v1.crossProduct(v2).unit();
            normalVectorList.add(normal);
        }

        vbuf = ByteBuffer.allocateDirect(verticeList.size() * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (float v: verticeList) {
            vbuf.put(v);
        }
        //vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
        this.n = n;
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, n);

        // side
        for (int i = 0; i < n; i++) {
            Vector normal = normalVectorList.get(i);
            int d = 1;
            if (normal.y < 0) d *= -1;
            gl.glNormal3f(normal.x * d, normal.y * d, normal.z * d);
            gl.glDrawArrays(GL10.GL_TRIANGLES, n + i*3, 3);
        }

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
