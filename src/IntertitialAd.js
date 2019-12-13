import {
    NativeModules,
    NativeEventEmitter
} from 'react-native'

const _int = NativeModules.TohkaIntertitital;

const IntertitialEvent = new NativeEventEmitter(NativeModules.TohkaIntertitital);

const showIntertitial = (_id) => {
    _id.showAd(_id)
}

export {IntertitialEvent,showIntertitial}


