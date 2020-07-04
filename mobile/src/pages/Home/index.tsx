import React, { useState, useEffect } from 'react';
import { Feather as Icon } from '@expo/vector-icons';
import { View, StyleSheet, Text, TextInput, TouchableOpacity, Alert} from 'react-native';
import { useNavigation } from '@react-navigation/native';
import Card from '../../components/Card';
import * as Location from 'expo-location';

import data from '../../../data.json';

const Home = () => {
	const [atual, setAtual] = useState('');
  const [final, setFinal] = useState('');
  const [initialPosition, setInitialPosition] = useState<[number, number]>([0, 0]);
  const navigation = useNavigation();
    
  useEffect(() => {
    async function loadPosition() {
      const { status } = await Location.requestPermissionsAsync();

      if (status !== 'granted') {
          Alert.alert('OOps', 'Precisamos de sua permissão para obter a localização');
          return;
      }

      const location = await Location.getCurrentPositionAsync();

      const { latitude, longitude } = location.coords;

      setInitialPosition([
          latitude,
          longitude
      ])

    }
    loadPosition();
  }, []);

  function handleNavigateToDetails() {
    navigation.navigate('Points', {
      latitude: initialPosition[0],
      longitude: initialPosition[1],
    });
  }

  function handleNavigateBack() {
    navigation.goBack();
  }

  return (
    <>
      <View style={styles.container}>
        <View style={{ marginTop: 20, marginBottom: 20, flexDirection: 'row', alignItems: 'center'}}>
          <TouchableOpacity onPress={() => handleNavigateBack}>
            <Icon name="arrow-left" size={20} color='black' />
          </TouchableOpacity>
          <Text style={{ marginLeft: 5, fontSize: 20}}>
            Fretes
          </Text>
        </View>
        <View style={{ width: "90%" }}>
          <View style={[styles.main]}>
            <View style={{ flexDirection: 'row', alignItems: 'center', backgroundColor: 'black', borderRadius: 10 }}>
              <Text style={{ padding: 20, color: 'white' }}>De:</Text>
              <TextInput
                style={[styles.input, {
                  width: '90%',
                  borderWidth: 1,
                  borderColor: 'white',
                  borderBottomColor: 'black',
                  borderBottomRightRadius: 0,
                }]}
                placeholder=" atasdasdsual"
                value={atual}
                autoCapitalize="characters"
                autoCorrect={false}
                onChangeText={setAtual}
              />
            </View>
            <View style={{ flexDirection: 'row', alignItems: 'center', backgroundColor: 'black', borderRadius: 10 }}>
              <Text style={{ padding: 14, color: 'white' }}>Para:</Text>
              <TextInput
                style={[styles.input, {
                  width: '90%',
                  borderTopRightRadius: 0,
                }]}
                placeholder=" atasdasdsual"
                value={final}
                autoCapitalize="characters"
                autoCorrect={false}
                onChangeText={setFinal}
              />
            </View>
          </View>
        </View>

        <View style={styles.footer}>
          
          {data.map(response => (
            <Card
              weight={response.weight}
              expected={response.expected}
              uf={response.address.uf}
              city={response.address.city}
              street={response.address.street}
              number={response.address.number}
              category={response.category}
              color="blue"
              latitude={response.latitude}
              longitude={response.longitude}
              freight={response.freight}
              next="Detail"
            />
          ))}

          {/* <Card weight="1kg" destination="Feira de Santana, BA" category="Frágil" color="blue" next="Detail" latitude={String(initialPosition[0])} longitude={String(initialPosition[1])}/>
          <Card destination="Fortaleza, CE" category="Urgente" color="red" expected='23/07/2020'/>
          <Card weight="3kg"destination="Rio de Janeiro, RJ" category="Frágil" color="green" expected='03/08/2020'/>
          <Card destination="Feira de Santana, BA" category="Frágil" color="brown" expected='12/08/2020'/>
          <Card />
          <Card />
          <Card /> */}

        </View>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
    container: {
      flex: 1,
      padding: 20,
    },
  
    main: {
      justifyContent: 'center',
    },

    input: {
      height: 60,
      backgroundColor: '#FFF',
      borderRadius: 10,
      marginBottom: 0,
      paddingHorizontal: 24,
      fontSize: 16,

    },
  
    title: {
      color: '#322153',
      fontSize: 32,
      fontFamily: 'Ubuntu_700Bold',
      maxWidth: 260,
      marginTop: 64,
    },
  
    description: {
      color: '#6C6C80',
      fontSize: 16,
      marginTop: 16,
      fontFamily: 'Roboto_400Regular',
      maxWidth: 260,
      lineHeight: 24,
    },
  
    footer: {
      marginTop: 20,
    },
  
    select: {},
  
    button: {
      backgroundColor: '#34CB79',
      height: 60,
      flexDirection: 'row',
      borderRadius: 10,
      overflow: 'hidden',
      alignItems: 'center',
      marginTop: 8,
    },
  
    buttonIcon: {
      height: 60,
      width: 60,
      backgroundColor: 'rgba(0, 0, 0, 0.1)',
      justifyContent: 'center',
      alignItems: 'center'
    },
  
    buttonText: {
      flex: 1,
      justifyContent: 'center',
      textAlign: 'center',
      color: '#FFF',
      fontFamily: 'Roboto_500Medium',
      fontSize: 16,
    }
  });

export default Home;