import {
    NativeModules
} from 'react-native'

const _lib = NativeModules.TohkaLib;

const Smaato = {
    setKeywords(keyword){
        _lib.setKeywords(keyword);
    },
    setAge(age){
        _lib.setAge(parseInt(age));
    },
    setLatLng(latitude,longitude,accuracy,type){
        _lib.setLatLng(latitude,longitude,parseFloat(accuracy),type);
    },
    setRegion(region){
        _lib.setRegion(region);
    },
    setLanguage(leLanguage){
        _lib.setLanguage(leLanguage)
    },
    setCoppa(coppa){
        _lib.setCoppa(coppa);
    }
}

export default Smaato;