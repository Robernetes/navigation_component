package com.vellamars.navcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vellamars.navcomponent.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    //@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener{
            val textContent:String = binding.editTextText.text.toString()
            binding.textView.text = textContent
            //val data = "Hola, mundo!"
            val action = HomeFragmentDirections.actionHomeFragmentToOtherFragment(textContent)
            findNavController().navigate(action)

            //val bundle = Bundle()
            //bundle.putString("content",textContent)
        }

        //val lifecycle = viewLifecycleOwner.lifecycle
        //lifecycle.addObserver(this)
    }


    //@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        super.onResume()
    }

    //@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        super.onPause()

    }

    //@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}