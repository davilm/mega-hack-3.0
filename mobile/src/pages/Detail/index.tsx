import React from 'react';
import { Feather as Icon, FontAwesome } from '@expo/vector-icons';
import { useNavigation, useRoute } from '@react-navigation/native'
import { RectButton } from 'react-native-gesture-handler';

import MapView, { Marker } from 'react-native-maps';

import { View, StyleSheet, TouchableOpacity, Text, Image, Linking } from 'react-native';
import Constants from 'expo-constants';

interface Params {
  weight: String,
  expected: String,
  uf: String,
  city: String,
  street: String,
  number: String,
  category: String,
  color: String,
  latitude: String,
  longitude: String,
  next: String,
}

const Detail = () => {
    const navigation = useNavigation();
    const route = useRoute();

    const routeParams = route.params as Params;
    
    const weight = routeParams.weight;
    const expected = routeParams.expected;
    const uf = routeParams.uf;
    const city = routeParams.city;
    const street = routeParams.street;
    const number = routeParams.number;
    const category = routeParams.category;
    const color = routeParams.color;
    const latitude = Number(routeParams.latitude);
    const longitude = Number(routeParams.longitude);

    function handleNavigateBack() {
      navigation.goBack();
    }

    function openGps (lat: Number, lng: Number) {
      Linking.openURL(`https://www.google.com/maps/search/?api=1&query=${lat}+${lng}`);
    }

    return (
        <>
            <View style={styles.container}>
                <TouchableOpacity onPress={handleNavigateBack}>
                    <Icon name="arrow-left" size={20} color='black' />
                </TouchableOpacity>

                <Text style={styles.pointName}>Carga {category}</Text>
                <Text style={[styles.pointItems, {paddingLeft: 20}]}>Carga: {weight}</Text>
                <Text style={[styles.pointItems, {paddingLeft: 20}]}>Entregar até: {expected}</Text>

                <View style={styles.address}>
                    <Text style={[styles.addressTitle, {paddingLeft: 20}]}>Endereço</Text>
                    <Text style={[styles.addressContent, {paddingLeft: 20}]}>{city}, {uf} - {street}, {number}</Text>
                </View>
            </View>
            <View style={styles.mapContainer}>
              <MapView 
                  style={styles.map} 
                  loadingEnabled={Number(routeParams.latitude) === 0}
                  initialRegion={{
                      latitude: Number(routeParams.latitude),
                      longitude: Number(routeParams.longitude),
                      latitudeDelta: 0.014,
                      longitudeDelta: 0.014,
                  }}
              >
                <Marker
                  style={styles.mapMarker}
                  onPress={() => handleNavigateBack()}
                  coordinate={{
                      latitude: Number(routeParams.latitude),
                      longitude: Number(routeParams.longitude),
                  }}
              >
                  <View style={styles.mapMarkerContainer}>
                      <Image style={styles.mapMarkerImage} source={{ uri: "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"}} />
                      <Text style={styles.mapMarkerTitle}>Frete</Text>
                  </View>
                </Marker>
              </MapView>
            </View>
            <View style={styles.footer}>
                <RectButton style={styles.button} onPress={() => openGps(latitude,longitude)}>
                    <FontAwesome name="whatsapp" size={20} color='#FFF' />
                    <Text style={styles.buttonText}>Map</Text>
                </RectButton>
            </View>
        </>
    );
};

const styles = StyleSheet.create({
    container: {
      flex: 1,
      paddingHorizontal: 32,
      paddingTop: 20 + Constants.statusBarHeight,
    },
  
    pointImage: {
      width: '100%',
      height: 120,
      resizeMode: 'cover',
      borderRadius: 10,
      marginTop: 32,
    },
  
    pointName: {
      color: '#322153',
      fontSize: 28,
      fontFamily: 'Ubuntu_700Bold',
      marginTop: 24,
    },
  
    pointItems: {
      fontFamily: 'Roboto_400Regular',
      fontSize: 16,
      lineHeight: 24,
      marginTop: 8,
      color: '#6C6C80'
    },
  
    address: {
      marginTop: 32,
    },
    
    addressTitle: {
      color: '#322153',
      fontFamily: 'Roboto_500Medium',
      fontSize: 16,
    },
  
    addressContent: {
      fontFamily: 'Roboto_400Regular',
      lineHeight: 24,
      marginTop: 8,
      color: '#6C6C80'
    },
  
    footer: {
      borderTopWidth: StyleSheet.hairlineWidth,
      borderColor: '#999',
      paddingVertical: 20,
      paddingHorizontal: 32,
      flexDirection: 'row',
      justifyContent: 'center'
    },
    
    button: {
      width: '48%',
      backgroundColor: '#34CB79',
      borderRadius: 10,
      height: 50,
      flexDirection: 'row',
      justifyContent: 'center',
      alignItems: 'center'
    },
  
    buttonText: {
      marginLeft: 8,
      color: '#FFF',
      fontSize: 16,
      fontFamily: 'Roboto_500Medium',
    },
    
  
    mapContainer: {
      flex: 1,
      width: '100%',
      borderRadius: 10,
      overflow: 'hidden',
      marginTop: 16,
    },
  
    map: {
      width: '100%',
      height: '100%',
    },
  
    mapMarker: {
      width: 90,
      height: 80, 
    },
  
    mapMarkerContainer: {
      width: 90,
      height: 70,
      backgroundColor: '#34CB79',
      flexDirection: 'column',
      borderRadius: 8,
      overflow: 'hidden',
      alignItems: 'center'
    },
  
    mapMarkerImage: {
      width: 90,
      height: 45,
      resizeMode: 'cover',
    },
  
    mapMarkerTitle: {
      flex: 1,
      fontFamily: 'Roboto_400Regular',
      color: '#FFF',
      fontSize: 13,
      lineHeight: 23,
    },
  });

export default Detail;