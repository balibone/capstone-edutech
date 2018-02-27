const getUserDetails = (userId) => {
  const fakeUserDetails = {
    id: userId,
    name: 'Nanda Derian',
    email: 'nandaderian@mail.com',
    school: 'NUS',
    faculty: 'School of Computing',
    major: 'Information Systems',
    year: 3,
    img: 'avatar.png',
  };
  return fakeUserDetails;
};

const updateUserDetails = (userId, userDetails) => {
  const placeholder = userId + userDetails;
  return placeholder;
};

export { getUserDetails, updateUserDetails };
