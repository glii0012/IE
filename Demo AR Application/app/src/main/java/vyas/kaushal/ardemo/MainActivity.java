package vyas.kaushal.ardemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

public class MainActivity extends AppCompatActivity {

    private Scene scene;

    private ModelRenderable sunRenderable;
    private ModelRenderable mercuryRenderable;
    private ModelRenderable venusRenderable;
    private ModelRenderable earthRenderable;
    private ModelRenderable marsRenderable;
    private ModelRenderable jupiterRenderable;
    private ModelRenderable saturnRenderable;
    private ModelRenderable uranusRenderable;
    private ModelRenderable neptuneRenderable;
    private ModelRenderable lunaRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomArFragment arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        scene = arFragment.getArSceneView().getScene();

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Sol.sfb"))
                .build()
                .thenAccept(modelRenderable -> sunRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Mercury.sfb"))
                .build()
                .thenAccept(modelRenderable -> mercuryRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Venus.sfb"))
                .build()
                .thenAccept(modelRenderable -> venusRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Earth.sfb"))
                .build()
                .thenAccept(modelRenderable -> earthRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Mars.sfb"))
                .build()
                .thenAccept(modelRenderable -> marsRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Jupiter.sfb"))
                .build()
                .thenAccept(modelRenderable -> jupiterRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Saturn.sfb"))
                .build()
                .thenAccept(modelRenderable -> saturnRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Uranus.sfb"))
                .build()
                .thenAccept(modelRenderable -> uranusRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Neptune.sfb"))
                .build()
                .thenAccept(modelRenderable -> neptuneRenderable = modelRenderable);

        ModelRenderable.builder()
                .setSource(this, Uri.parse("Luna.sfb"))
                .build()
                .thenAccept(modelRenderable -> lunaRenderable = modelRenderable);

        showSolarSystem();
    }

    private Node createSolarSystem() {
        RotatingNode sunNode = new RotatingNode();
        sunNode.setLocalPosition(new Vector3(0f, 0f, -3f));
        sunNode.setRenderable(sunRenderable);

        RotatingNode earthNode = new RotatingNode();
        earthNode.setParent(sunNode);
        earthNode.setLocalScale(new Vector3(0.75f, 0.75f, 0.75f));
        earthNode.setLocalPosition(new Vector3(2f, 0f, 0f));
        earthNode.setRenderable(earthRenderable);

        RotatingNode lunaNode = new RotatingNode();
        lunaNode.setParent(earthNode);
        lunaNode.setLocalScale(new Vector3(0.5f, 0.5f, 0.5f));
        lunaNode.setLocalPosition(new Vector3(1f, 0.25f, 0f));
        lunaNode.setRenderable(lunaRenderable);

        return sunNode;
    }

//    private Node createSolarSystem() {
//        Node marsNode = new Node();
//        marsNode.setLocalPosition(new Vector3(0f, 0f, -2f));
//        marsNode.setRenderable(marsRenderable);
//
//        Node sunNode = new Node();
//        sunNode.setParent(marsNode);
//        sunNode.setLocalPosition(new Vector3(-6f, 0f, 0f));
//        sunNode.setRenderable(sunRenderable);
//
//        Node mercuryNode = new Node();
//        mercuryNode.setParent(marsNode);
//        mercuryNode.setLocalPosition(new Vector3(-4.5f, 0f, 0f));
//        mercuryNode.setRenderable(mercuryRenderable);
//
//        Node venusNode = new Node();
//        venusNode.setParent(marsNode);
//        venusNode.setLocalPosition(new Vector3(-3f, 0f, 0f));
//        venusNode.setRenderable(venusRenderable);
//
//        Node earthNode = new Node();
//        earthNode.setParent(marsNode);
//        earthNode.setLocalPosition(new Vector3(-1.5f, 0f, 0f));
//        earthNode.setRenderable(earthRenderable);
//
//        Node jupiterNode = new Node();
//        jupiterNode.setParent(marsNode);
//        jupiterNode.setLocalPosition(new Vector3(1.5f, 0f, 0f));
//        jupiterNode.setRenderable(jupiterRenderable);
//
//        Node saturnNode = new Node();
//        saturnNode.setParent(marsNode);
//        saturnNode.setLocalPosition(new Vector3(3f, 0f, 0f));
//        saturnNode.setRenderable(saturnRenderable);
//
//        Node uranusNode = new Node();
//        uranusNode.setParent(marsNode);
//        uranusNode.setLocalPosition(new Vector3(4.5f, 0f, 0f));
//        uranusNode.setRenderable(uranusRenderable);
//
//        Node neptuneNode = new Node();
//        neptuneNode.setParent(marsNode);
//        neptuneNode.setLocalPosition(new Vector3(6f, 0f, 0f));
//        neptuneNode.setRenderable(neptuneRenderable);
//
//        return marsNode;
//    }

    private void showSolarSystem() {
        Node displayNode = createSolarSystem();
        scene.addChild(displayNode);
    }
}
