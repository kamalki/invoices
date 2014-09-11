json.array!(@users) do |user|
  json.extract! user, :id, :name, :gender, :age, :email_id, :mobile, :city, :state
  json.url user_url(user, format: :json)
end
