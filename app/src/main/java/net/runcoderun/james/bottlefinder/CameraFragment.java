package net.runcoderun.james.bottlefinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import static com.google.android.gms.vision.CameraSource.CAMERA_FACING_BACK;

/**
 * Created by james on 06/12/17.
 */

public class CameraFragment extends Fragment implements SurfaceHolder.Callback {
    CameraSource cameraSource;
    SurfaceView surfaceView;
    TextRecognizer textRecognizer;

    public static CameraFragment newInstance() {
        CameraFragment cameraFragment = new CameraFragment();
        return cameraFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        surfaceView = this.getActivity().findViewById(R.id.camera_surface_view);
        textRecognizer = new TextRecognizer.Builder(getContext()).build();

        cameraSource = new CameraSource.Builder(this.getContext(), textRecognizer)
            .setRequestedPreviewSize(640, 480)
            .setRequestedFps(20.0f)
            .setFacing(CAMERA_FACING_BACK)
            .build();
    }

    @Override
    public void onPause() {
        super.onPause();
        cameraSource.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cameraSource.release();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_camera, container, false);
        surfaceView = view.findViewById(R.id.camera_surface_view);

        surfaceView.getHolder().addCallback(this);

        return view;
    }

    @Override

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            cameraSource.start(surfaceView.getHolder());
        } catch (IOException e) {
            Toast.makeText(getContext(), "I/O problem happened", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        cameraSource.stop();
    }

    //    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        String selItem = (String) l.getItemAtPosition(position);
//        SearchFragment searchFragment = SearchFragment.newInstance(Integer.toString((Integer) hm.get(selItem)));
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.search_fragment, searchFragment);
//        fragmentTransaction.commit();
//
//        ViewPager viewPager = getActivity().findViewById(R.id.viewpager);
//        viewPager.setCurrentItem(1, true);
//    }
}
