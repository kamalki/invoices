class AddreservationIdtocustomer < ActiveRecord::Migration
  def change
  	add_column :customer, :reservation_id, :string
  end
end
