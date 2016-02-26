package com.cml.second.app.baby.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cml.second.app.baby.R;
import com.cml.second.app.baby.utils.AppUtils;

import java.io.File;
import java.io.InputStream;

import butterknife.Bind;

/**
 * Created by cmlBeliever on 2016/2/26.
 */
public class PictureFragment extends BaseFragment {
    /**
     * 选择文件
     */
    public static final int TYPE_FROM_FILE = 1;
    /**
     * 拍照上传
     */
    public static final int TYPE_FROM_CAMERA = 2;

    private static final int REQUEST_CAMEAR = 1;

    private static final String EXTRA_TYPE = "PictureFragment。EXTRA_TYPE";
    private static final String EXTRA_CONTENT = "PictureFragment。EXTRA_CONTENT";

    private int type;
    private Uri contentUri;
    private Uri cameraFileUri;//拍照保存路径

    @Bind(R.id.picture_desc)
    TextView descView;
    @Bind(R.id.picture_img)
    ImageView imgView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(EXTRA_TYPE);
            contentUri = getArguments().getParcelable(EXTRA_CONTENT);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        imgView.setImageResource(R.drawable.ic_launcher);
        if (TYPE_FROM_CAMERA == type) {
            //TODO 启动相机权限
            getPictureFromCamera();
        }
    }

    private File file;

    private void getPictureFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(AppUtils.getPicturePath(), "file" + System.currentTimeMillis() + ".jpg");
        cameraFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CAMEAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMEAR) {//拍照选择文件
                try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                    imgView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
//                    Glide.with(getActivity()).load(getActivity().getContentResolver().openInputStream(data.getData())).centerCrop().crossFade().into(imgView);
//                    Glide.with(getActivity()).load("file://"+file.getAbsolutePath()).centerCrop().crossFade().into(imgView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Glide.with(getActivity()).load(data.getData()).centerCrop().crossFade().into(imgView);
            }
        }
        descView.setText(data.getDataString() + ",result:" + resultCode + "," + requestCode + "," + file.getAbsolutePath() + ":" + file.exists());
        Toast.makeText(getActivity(), "result:" + resultCode + "," + data.getData(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_picture;
    }

    @Override
    public int getTitle() {
        return R.string.title_picture;
    }

    /**
     * 获取启动此fragment
     *
     * @param type
     * @param contentUri
     * @return
     */
    public static Bundle getStartBundle(int type, Uri contentUri) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_TYPE, type);
        bundle.putParcelable(EXTRA_CONTENT, contentUri);
        return bundle;
    }
}
