import React from 'react';

const centerStyle = {
  textAlign: 'center'
};

const Message = ({ message }) => 
    <div style={centerStyle}><h2>{message}</h2></div>

export default Message;
