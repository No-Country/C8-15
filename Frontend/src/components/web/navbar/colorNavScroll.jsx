
import { useScrollTrigger  } from "@mui/material";
import React from "react";


const ScrollColorNav = props => {

const trigger = useScrollTrigger({
  disableHysteresis: true,
  threshold: 0 ,
  target: props.window ? window() : undefined
});

    return React.cloneElement(props.children, {

      style: {
        backgroundColor: trigger ? "#FFFFFF" : 'rgb( 255, 255,255 ,.5)',
        transition : trigger ? '0.6s' : '0.9s',
      }
    });
};
    const ScrollColor = props => {
      return <ScrollColorNav {...props}>{props.children}</ScrollColorNav>
    };

export default ScrollColor;  
