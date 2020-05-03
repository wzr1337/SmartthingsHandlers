/**
 *  Copyright 2017 Edvald Eysteinsson
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  IKEA Trådfri
 *
 *  This handler is written so that the bulbs behave a bit more like traditional halogen bulbs and the ones i modeled it
 *  after is https://www.osram.com/osram_com/products/lamps/halogen-lamps/halopar/halopar-16-gu10gz10-star/index.jsp
 *  they have a color rendering index of 100 at full brightness and that is equivalent to 3200 kelvin. The level at 1%
 *  will use 2200 kelvin and each percent will increse the temperature by 10 ending up at 3190 at 100%
 *
 *  Author: Edvald Eysteinsson
 *  Date: 2017-03-18
 */
metadata {
  definition (name: "IKEA-Tradfri mono color", namespace: "edvaldeysteinsson", author: "Edvald Eysteinsson") {
    capability "Actuator"
    capability "Configuration"
    capability "Health Check"
    capability "Refresh"
    capability "Switch"
    capability "Switch Level"
    capability "Light"

	
    // Trådfri bulbs
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E27 WS opal 980lm", deviceJoinName: "TRÅDFRI bulb E27 WS opal 980lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E27 WS opal 980lm", deviceJoinName: "TRÅDFRI bulb E27 WS opal 980lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E27 WS clear 950lm", deviceJoinName: "TRÅDFRI bulb E27 WS clear 950lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E26 WS opal 980lm", deviceJoinName: "TRÅDFRI bulb E26 WS opal 980lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E26 WS opal 980lm", deviceJoinName: "TRÅDFRI bulb E26 WS opal 980lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E26 WS clear 950lm", deviceJoinName: "TRÅDFRI bulb E26 WS clear 950lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E14 WS opal 400lm", deviceJoinName: "TRÅDFRI bulb E14 WS opal 400lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb E12 WS opal 400lm", deviceJoinName: "TRÅDFRI bulb E12 WS opal 400lm"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "TRADFRI bulb GU10 WS 400lm", deviceJoinName: "TRÅDFRI bulb GU10 WS 400lm"
    
    // FLOALT panels
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "FLOALT panel WS 30x30", deviceJoinName: "FLOALT panel WS 30x30"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "FLOALT panel WS 30x90", deviceJoinName: "FLOALT panel WS 30x90"
    fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, 0300, 0B05, 1000", outClusters: "0005, 0019, 0020, 1000", manufacturer: "IKEA of Sweden",  model: "FLOALT panel WS 60x60", deviceJoinName: "FLOALT panel WS 60x60"
  }

  preferences {
    input name: "delay", type: "number", title: "Delay between level and color temperature change in milliseconds", defaultValue: 0, displayDuringSetup: true, required: false
  }

  // UI tile definitions
  tiles(scale: 2) {
    multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
      tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
        attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
        attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
        attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#00a0dc", nextState:"turningOff"
        attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
      }
      
      tileAttribute ("device.level", key: "SLIDER_CONTROL") {
        attributeState "level", action:"setLevel"
        }
    }

    standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat", width: 2, height: 1) {
      state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
    } 

    main(["switch"])
    details(["switch", "refresh"])
  }
}

// parse events into attributes
def parse_new(description) {
  def results = []

  def map = description
  if (description instanceof String)  {
    map = stringToMap(description)
  }

  if (map?.name && map?.value) {
    results << createEvent(name: "${map?.name}", value: "${map?.value}")
  }

  results
}

// Parse incoming device messages to generate events
def parse(String description) {
  def event = zigbee.getEvent(description)

  if (event) {
    if (event.name != "level" || (event.name=="level" && event.value > 0)) {
      sendEvent(event)
    }
  } else {
    def cluster = zigbee.parse(description)

    if (cluster && cluster.clusterId == 0x0006 && cluster.command == 0x07) {
      if (cluster.data[0] == 0x00) {
        sendEvent(name: "checkInterval", value: 60 * 12, displayed: false, data: [protocol: "zigbee", hubHardwareId: device.hub.hardwareID])
      } else {
        log.warn "ON/OFF REPORTING CONFIG FAILED- error code:${cluster.data[0]}"
      }
    } else {
      log.warn "DID NOT PARSE MESSAGE for description : $description"
      log.debug "${cluster}"
    }
  }
}

def off() {
  zigbee.off()
}

def on() {
  zigbee.on()
}

def setLevel(value) {
  if(value == 0){
    zigbee.setLevel(value)
  } else { 
    zigbee.setLevel(value)
  }
}

/**
* PING is used by Device-Watch in attempt to reach the Device
* */
def ping() {
  return zigbee.onOffRefresh()
}

def refresh() {
  zigbee.onOffRefresh() + zigbee.levelRefresh() + zigbee.onOffConfig(0, 300) + zigbee.levelConfig()
}

def configure() {
  // Device-Watch allows 2 check-in misses from device + ping (plus 1 min lag time)
  // enrolls with default periodic reporting until newer 5 min interval is confirmed
  sendEvent(name: "checkInterval", value: 2 * 10 * 60 + 1 * 60, displayed: false, data: [protocol: "zigbee", hubHardwareId: device.hub.hardwareID])

  // OnOff minReportTime 0 seconds, maxReportTime 5 min. Reporting interval if no activity
  refresh()
}

def installed() {
  if ((device.currentState("level")?.value == null) || (device.currentState("level")?.value == 0)) {
    sendEvent(name: "level", value: 100)
  }
}