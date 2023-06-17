package com.vellamars.navcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vellamars.navcomponent.databinding.FragmentOtherBinding


class OtherFragment : Fragment() {

    private var _binding: FragmentOtherBinding? = null
    private val binding get() = _binding!!
    private var myInfo: Any? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOtherBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val args = OtherFragmentDirections.fromBundle(requireArguments())
        val args = OtherFragmentArgs.fromBundle(requireArguments())
        val datos = args.datos
        binding.textView4.text = datos.toString()


//        val args = this.arguments
//        val data = args?.get("content")
//        myInfo = data
//        binding.textView4.text = myInfo.toString()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

    }


}