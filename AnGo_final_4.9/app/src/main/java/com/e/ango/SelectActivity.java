package com.e.ango;

import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.view.View

class SelectActivity : AppCompatActivity() {
private var selectedIndex: Int = 0;

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = intent.getIntExtra("layout_file_id", R.layout.activity_select)
        setContentView(layout)
        System.out.println("ACTIVITY : 1")

        val motionLayout = findViewById<MotionLayout>(R.id.motion_container)
        System.out.println("ACTIVITY : 2")

        val v1 = findViewById<View>(R.id.v1)
        val v2 = findViewById<View>(R.id.v2)

        System.out.println("ACTIVITY : 3")
        v1.setOnClickListener {
        System.out.println("ACTIVITY : 4")
        if (selectedIndex == 0) return@setOnClickListener

            motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
                    motionLayout.transitionToEnd()
                    selectedIndex = 0;
                    }
                    v2.setOnClickListener {
                    System.out.println("ACTIVITY : 5")
                    if (selectedIndex == 1) return@setOnClickListener

            motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition

                    motionLayout.transitionToEnd()
                    selectedIndex = 1;
                    }

                    }
                    }