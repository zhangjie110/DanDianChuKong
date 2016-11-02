package test.bwei.com.dandianchukong;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        iv.setOnTouchListener(new View.OnTouchListener() {
            private float x;
            private float y;
            // 用来操作图片的模型
            private Matrix oldMatrix = new Matrix();
            private Matrix newMatrix = new Matrix();

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) { // 判断操作类型
                    case MotionEvent.ACTION_DOWN:
                        //按下时记住x,y的坐标
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        oldMatrix.set(iv.getImageMatrix());
                        break;
                    case MotionEvent.ACTION_MOVE://移动时
                        //用另一个模型记住按下时的位置
                        newMatrix.set(oldMatrix);
                        //移动模型
                        newMatrix.setTranslate(motionEvent.getX() - x, motionEvent.getY() - y);
                        break;

                }
                //把图片放入移动后的模型中
                iv.setImageMatrix(newMatrix);
                return true;
            }
        });
    }
}
