/*
 * Naming Rule
 *  1. Request to node server action: nodeActionName
 *  2. Action consumes request: nodeActionNameApi
 *  3. Redux action: actionNameAction
 */

import axios from 'axios';
import { get } from 'lodash';
import { NODE_SERVER_URL } from 'actions/constants';
import { DATA } from 'constants/common';
import { handleRequest } from 'api/utils';
import { LOADING, setLoading, setError } from 'actions/common';
import { HTTP_STATUS } from 'constants/http';

const authRootUrl = NODE_SERVER_URL.AUTH.ROOT;
const authUrl = NODE_SERVER_URL.AUTH;
const sendingOTPUrl = `${authRootUrl}${authUrl.SENDING_OTP}`;
const verifyingOTPUrl = `${authRootUrl}${authUrl.VERIFY_OTP}`;
const creatingUserUrl = `${authRootUrl}${authUrl.CREATING_USER}`;
const existingUserUrl = `${authRootUrl}${authUrl.EXISTING_USER}`;

// Action API to Node Server

const nodeUsers = () => axios.get(authUrl);
const nodeSendingOTP = (countryCode, phoneNumber) =>
  axios.get(`${sendingOTPUrl}/${countryCode}/${phoneNumber}`);
const nodeVerifyingOTP = (countryCode, phoneNumber, otpCode) =>
  axios.get(`${verifyingOTPUrl}/${countryCode}/${phoneNumber}/${otpCode}`);
const nodeCreatingUser = data => axios.post(creatingUserUrl, data);
const nodeExistingUser = phone => axios.get(`${existingUserUrl}/${phone}`);

// Redux constants

export const SET_USERS = 'AUTH.SET_USERS';
export const SET_PHONE_NUMBER = 'AUTH.SET_PHONE_NUMBER';
export const SENDING_OTP = 'AUTH.SENDING_OTP';
export const VERIFYING_OTP = 'AUTH.VERIFYING_OTP';
export const CREATING_USER = 'AUTH.CREATING_USER';
export const EXISTING_USER = 'AUTH.EXISTING_USER';

// Redux action

const setUsersAction = payload => ({
  type: SET_USERS,
  payload,
});
export const setPhoneNumberAction = payload => ({
  type: SET_PHONE_NUMBER,
  payload,
});
export const sendingOTPAction = payload => ({
  type: SENDING_OTP,
  payload,
});
export const verifyingOTPAction = payload => ({
  type: VERIFYING_OTP,
  payload,
});
export const creatingUserAction = payload => ({
  type: CREATING_USER,
  payload,
});
export const existingUserAction = payload => ({
  type: EXISTING_USER,
  payload,
});

// Consuming actions

export const nodeCreatingUserApi = phone => async dispatch => {
  dispatch(setLoading(LOADING.ON));
  const data = { phone };
  const [result, error] = await handleRequest(nodeCreatingUser, [data]);
  const code = get(result, DATA.CODE) || get(error, DATA.CODE);
  const success = get(result, DATA.SUCCESS) || get(error, DATA.SUCCESS);
  if (code === HTTP_STATUS.INTERNAL_ERROR) {
    const message = get(result, DATA.MESSAGE) || get(error, DATA.MESSAGE);
    dispatch(setError(message));
  }
  dispatch(creatingUserAction(success));
  dispatch(setLoading(LOADING.OFF));
};
export const nodeUsersApi = () => async dispatch => {
  dispatch(setLoading(LOADING.ON));
  const [result, error] = await handleRequest(nodeUsers, []);
  if (error) {
    const message = get(error, DATA.MESSAGE);
    dispatch(setError(message));
  } else {
    dispatch(setUsersAction(result));
  }
  dispatch(setLoading(LOADING.OFF));
};
export const nodeSendingOTPApi = (
  countryCode,
  phoneNumber,
) => async dispatch => {
  dispatch(setLoading(LOADING.ON));
  const [result, error] = await handleRequest(nodeSendingOTP, [
    countryCode,
    phoneNumber,
  ]);
  if (error) {
    const message = get(error, DATA.MESSAGE);
    dispatch(setError(message));
  } else {
    const sendOTPStatus = get(result, DATA.SUCCESS);
    dispatch(sendingOTPAction(sendOTPStatus));
  }
  dispatch(setLoading(LOADING.OFF));
};
export const nodeVerifyingOTPApi = (
  countryCode,
  phoneNumber,
  otpCode,
) => async dispatch => {
  dispatch(setLoading(LOADING.ON));
  const [result, error] = await handleRequest(nodeVerifyingOTP, [
    countryCode,
    phoneNumber,
    otpCode,
  ]);
  if (error) {
    const message = get(error, DATA.MESSAGE);
    dispatch(setError(message));
  } else {
    const verifyOTPStatus = get(result, DATA.SUCCESS);
    dispatch(verifyingOTPAction(verifyOTPStatus));
  }
  dispatch(setLoading(LOADING.OFF));
};
export const nodeExistingUserApi = phone => async dispatch => {
  dispatch(setLoading(LOADING.ON));
  const [result, error] = await handleRequest(nodeExistingUser, [phone]);
  const data = get(result, DATA.ROOT) || get(error, DATA.ROOT);
  const code = get(data, 'code');
  if (code === HTTP_STATUS.INTERNAL_ERROR) {
    const message = get(data, 'message');
    dispatch(setError(message));
  }
  dispatch(existingUserAction(data));
  dispatch(setLoading(LOADING.OFF));
};
