package com.example.fifthtry

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView


class MainActivity : AppCompatActivity(), SensorEventListener  {

    lateinit var you: ImageView
    lateinit var chronometer:Chronometer
    lateinit var dont: ImageView
    var change = false
    lateinit var accel: Sensor
    var accelValues = FloatArray(3)
    lateinit var back:View
    lateinit var star: ImageView
    var running:Boolean = false
    var hero:Int=R.drawable.crt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        star = findViewById(R.id.star)
       // texto=findViewById(R.id.textView)
        you = findViewById(R.id.imageView)
        dont= findViewById(R.id.dont)
        back=findViewById(R.id.background)
        dont.setImageResource(R.drawable.crt1)

        val animator = ObjectAnimator.ofFloat(dont, View.TRANSLATION_Y, 500f)
        animator.repeatCount = 8
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()

        val sMgr = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accel = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sMgr.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI)

        val chronometer =
            findViewById(R.id.chronometerExample) as Chronometer //El chronometro es  un componente en el xml. Que tiene sus propios metodos.
        chronometer.start()
        running=true


        val notification = Notification.Builder(this).setContentTitle("The adventure starts")
            .setContentText("Time is now ${System.currentTimeMillis()}").
            setSmallIcon(R.drawable.crt).build()

        val nMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nMgr.notify(1, notification) // id is a unique ID for this notification

          //  getColision(you, dont)


        //chronometer.setBase(SystemClock.elapsedRealtime())

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //Para llamar al menu de opciones que es un xml con los elementos que tenemos en la barra superior.
        val inflater =
            menuInflater //El inflate es un inflador , es decir se crea el menu a partir del xml
        inflater.inflate(R.menu.menu, menu) // Se le llama
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.instrucciones -> {

                true
            }
            R.id.Hipotenochas -> true
            R.id.bandera -> {
                true
            }
            R.id.bomba -> {
                true
            }
            R.id.exclamacion -> {
                true
            }
            R.id.comenzar -> {
                true
            }
            R.id.configuracion -> true
            R.id.facil -> {

                true
            }
            R.id.medio -> {

                true
            }
            R.id.dificil -> {

                true
            }
            R.id.salir -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun getColision(a: ImageView, b: ImageView) {
     val point1 = IntArray(2) //x1-x2
     val point2 = IntArray(2) //y1-y2
     a.getLocationOnScreen(point1)
     b.getLocationOnScreen(point2)
     val rc1 = Rect(
         point1[0], point1[1],
         point1[0] + a.getWidth(), point1[1] + a.getHeight()
     )
     val rc2 = Rect(
         point2[0], point2[1],
         point2[0] + b.getWidth(), point2[1] + b.getHeight()
     )
     if (Rect.intersects(rc1, rc2)) {
         running=false
         chronometer.stop()

     } else {
       //  texto.setText("Free")
     }
 }
    private fun moveEast(data: Float) {
        you.setImageResource(hero)
        val animator = ObjectAnimator.ofFloat(you, View.TRANSLATION_X, -160 * data)
        animator.start()
    }
    private fun moveWest(data: Float){
        you.setImageResource(R.drawable.crt)
        val animator = ObjectAnimator.ofFloat(you, View.TRANSLATION_X, -160 * data)
        animator.start()
    }
    private fun moveNorth(data: Float){
        you.setImageResource(R.drawable.crt)
        val animator = ObjectAnimator.ofFloat(you, View.TRANSLATION_Y, 160 * data)
        // animator.disableViewDuringAnimation(righButton )
        // animator.disableViewDuringAnimation(leftButton)
        animator.start()
    }
    private fun moveSouth(data: Float) {
        you.setImageResource(R.drawable.crt)
        val animator = ObjectAnimator.ofFloat(you, View.TRANSLATION_Y, 160 * data)
        animator.start()

    }
    private fun rain1(){
    // Create a new star view in a random X position above the container.
        // Make it rotateButton about its center as it falls to the bottom.

        // Local variables we'll need in the code below
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()

        // Create the new star (an ImageView holding our drawable) and add it to the container
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)
        newStar.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        container.addView(newStar)

        // Scale the view randomly between 10-160% of its default size

        // Position the view at a random place between the left and right edges of the container
        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        // Create an animator that moves the view from a starting position right about the container
        // to an ending position right below the container. Set an accelerate interpolator to give
        // it a gravity/falling feel
        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)
        mover.interpolator = AccelerateInterpolator(1f)

        // Create an animator to rotateButton the view around its center up to three times
        val rotator = ObjectAnimator.ofFloat(
            newStar, View.ROTATION,
            (Math.random() * 1080).toFloat()
        )
        rotator.interpolator = LinearInterpolator()

        // Use an AnimatorSet to play the falling and rotating animators in parallel for a duration
        // of a half-second to two seconds
        val set = AnimatorSet()
        set.playTogether(mover, rotator)
        set.duration = (Math.random() * 1500 + 500).toLong()

        // When the animation is done, remove the created view from the container
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        // Start the animation
        set.start()
    }
    private fun rain0(){
        // Create a new star view in a random X position above the container.
        // Make it rotateButton about its center as it falls to the bottom.

        // Local variables we'll need in the code below
        val container = star.parent as ViewGroup
        val containerW = container.width
        val containerH = container.height
        var starW: Float = star.width.toFloat()
        var starH: Float = star.height.toFloat()

        // Create the new star (an ImageView holding our drawable) and add it to the container
        val newStar = AppCompatImageView(this)
        newStar.setImageResource(R.drawable.ic_star)

            newStar.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            container.addView(newStar)

        // Scale the view randomly between 10-160% of its default size

        // Position the view at a random place between the left and right edges of the container
        newStar.translationX = Math.random().toFloat() * containerW - starW / 2

        // Create an animator that moves the view from a starting position right about the container
        // to an ending position right below the container. Set an accelerate interpolator to give
        // it a gravity/falling feel
        val mover = ObjectAnimator.ofFloat(newStar, View.TRANSLATION_Y, -starH, containerH + starH)
        mover.interpolator = AccelerateInterpolator(1f)

        // Use an AnimatorSet to play the falling and rotating animators in parallel for a duration
        // of a half-second to two seconds
        val set = AnimatorSet()
        set.playTogether(mover)

        // When the animation is done, remove the created view from the container
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                container.removeView(newStar)
            }
        })
        // Start the animation
        set.start()
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                view.isEnabled = false
            }

            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true
            }
        })
    }
    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    override fun onSensorChanged(event: SensorEvent) {
        if(event.sensor == accel) {
            // Copy the current values into the acceleration array
            accelValues = event.values.copyOf()
            if (accelValues[0]<=-0.2f){moveEast(accelValues[0])}
            if (accelValues[0]>=0.2f){moveWest(accelValues[0])}
            if(accelValues[1]<=-0.2f){moveSouth(accelValues[1])}
            if(accelValues[1]>=0.2f){moveNorth(accelValues[1])}
        }
    }
}
